package com.example.userservice.Controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.userservice.DataMining.DataGenerator;
import com.example.userservice.Entities.Privilege;
import com.example.userservice.Entities.Role;
import com.example.userservice.Entities.User;
import com.example.userservice.Entities.VerificationToken;
import com.example.userservice.Model.LoginRequest;
import com.example.userservice.Model.PasswordResetModel;
import com.example.userservice.Repository.UserRepository;
import com.example.userservice.Security.JWTUtil;
import com.example.userservice.Services.Privilege.IPrivilegeService;
import com.example.userservice.Services.Role.IRoleService;
import com.example.userservice.Services.User.IUserService;
import com.example.userservice.Services.User.VerificationTokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.core.ResolvableType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {


IUserService iUserService;
IPrivilegeService iPrivilegeService;
IRoleService iRoleService;

VerificationTokenService verificationTokenService;
    @GetMapping("/helloUser")
    public String Hello(){

        return "bills hello from microservices user !";

    }
    AuthenticationManager authenticationManager;



    @PostMapping("/upload/{userId}")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file ,@PathVariable("userId")int userId ){
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Image file is required.");
        }
        try {
            User user = iUserService.getUserById(userId);

            // Enregistrement du fichier dans le dossier de stockage
            String fileName = file.getOriginalFilename();
            String filename = file.getOriginalFilename();
            String newFileName = FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
           // File serverFile = new File (context.getRealPath("/Images/"+File.separator+newFileName));
            user.setImage(fileName);
            File targetFile = new File("C:/Users/Kouki/Desktop/pi final/piFront-master/src/assets/upload", fileName);
            FileOutputStream outputStream = new FileOutputStream(targetFile);
            IOUtils.copy(file.getInputStream(), outputStream);
            outputStream.close();

            // Logique pour insérer le chemin de l'image dans la base de données
            // Insérez ici votre code pour enregistrer le chemin de l'image dans votre entité User ou toute autre entité pertinente

            return ResponseEntity.ok("Image uploaded successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image.");
        }

    }
    @PostMapping("/signin")
    public void authenticateUser(HttpServletRequest request,HttpServletResponse response,@RequestBody LoginRequest requestlogin)throws IOException {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(requestlogin.getEmail(), requestlogin.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //j'ai l'ajouter car j'ai besoin aussi le role d'aprés simple objet user

        org.springframework.security.core.userdetails.User user =(org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        com.example.userservice.Entities.User user1 = iUserService.laodUserByUserName(user.getUsername());
        user1.setLastLogin(Calendar.getInstance().getTime());
        Algorithm algorithm = Algorithm.HMAC256(JWTUtil.SECRET);
        String jwtAccessToken = JWT.create()
                .withSubject(user.getUsername())
                //sexpire dans 5min en millissecondes
                .withExpiresAt(new Date(System.currentTimeMillis()+ JWTUtil.EXP_ACCESS_TOKEN))
                //nom de l'app qui a gérer le token (on va mettre url de la requete)
                .withIssuer(request.getRequestURL().toString())
                //convertit la liste des authoritys de lobjet user spring en liste de priviléges
                .withClaim("privileges",user.getAuthorities().stream().map(ga->ga.getAuthority()).collect(Collectors.toList()))
                .withClaim("role",user1.getRole().getRoleName())
                .sign(algorithm);

        String jwtRefreshToken = JWT.create()
                .withSubject(user.getUsername())
                //sexpire dans 15 min en millissecondes plus long que acces
                .withExpiresAt(new Date(System.currentTimeMillis()+JWTUtil.EXP_REFRESH_TOKEN))
                //nom de l'app qui a gérer le token (on va mettre url de la requete)
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);

        Map<String , String> idToken = new HashMap<>();
        idToken.put("accessToken",jwtAccessToken);
        idToken.put("refreshToken",jwtRefreshToken);
        response.setHeader(JWTUtil.AUTH_HEADER,jwtAccessToken);

        response.setContentType("application/json");
        //pour serialiser un objet en json avec entrer et sortie
        new ObjectMapper().writeValue(response.getOutputStream(),idToken);
        //renvoie dans un objet json dans le body
        // String jsonResponse = "{\"token\":\"" + jwtAccessToken + "\"}";
        //response.getOutputStream().write(jsonResponse.getBytes());

    }


    @GetMapping("/requestPasswordReset/{email}")
    public Response requestPasswordReset(@PathVariable("email") String email) throws Exception {
        return iUserService.requestPasswordReset(email);
    }
    @PostMapping("/password-reset")
    public Response resetPassword(@RequestBody PasswordResetModel passwordResetModel,@QueryParam("token") String token) {
        System.out.println(passwordResetModel);
        return iUserService.resetPassword(token , passwordResetModel.getNewPassword() , passwordResetModel.getConfirmPassword());
    }

    @GetMapping("/AllUsers")
    @ResponseBody
    public List<User> getAllUsers(){

        System.out.println("houni allll user");
        return iUserService.getAllUsers();
    }


    @PostMapping("/addUser")
    @ResponseBody
    public Response addUser (@RequestBody User user){
        if (user.getEmail() == ""){
            return Response.status(Response.Status.BAD_REQUEST).entity("Email Obligatoire").build();
        }
        if (user.getPassword() == ""){
            return Response.status(Response.Status.BAD_REQUEST).entity("Password Obligatoire").build();
        }
        if (user.getLname() == "" || user.getFname()==""){
            return Response.status(Response.Status.BAD_REQUEST).entity("Nom et prenom Obligatoires").build();
        }
        if (iUserService.laodUserByUserName(user.getEmail()) != null){
            return Response.status(Response.Status.CONFLICT).entity("email déja exist  ").build();
        }else {
            iUserService.addUser(user);
            return Response.status(Response.Status.CREATED).entity(user).build();
        }


    }

    @GetMapping("/getUserById/{id}")
    @ResponseBody
    public  User  getUserById(@PathVariable("id") int id){

       return iUserService.getUserById(id);

    }



    @DeleteMapping("/deleteUser/{id}")
    private void deleteUser(@PathVariable("id") int id)
    {
            iUserService.deleteUser(id);




    }

    @PutMapping("/updateUser")
    private User updateUser(@RequestBody User user)
    {
       return iUserService.updateUser(user);

    }

    @PutMapping("/desActiverUser/{id}")
    private Response desActiverUser(@PathVariable("id") int id)
    {
        User user = iUserService.getUserById(id);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("user introuvable").build();
        }else if (iUserService.desActiverCompteUser(id) == true){
            return Response.status(Response.Status.OK).entity("user activer").build();
        }else {
            return Response.status(Response.Status.OK).entity("user desactiver").build();
        }

    }



    @PostMapping("/affecterUserRole/{idUser}/{idRole}")
    public User affecterUserRole(@PathVariable("idUser") int idUser,@PathVariable("idRole") int idRole) {
        return iUserService.affecterUserRole(idUser,idRole);
    }

    @PostMapping("/addUserWithRoleAndAffectPrivileges")
    public Response addUserWithRoleAndAffectPrivileges(@RequestBody User user) {
        if (iUserService.laodUserByUserName(user.getEmail()) != null){
            return Response.status(Response.Status.CONFLICT).entity("email déja exist  ").build();
        }else{
            iUserService.addUserWithRoleAndAffectPrivileges(user);
            return Response.status(Response.Status.OK).entity(user).build();

        }

    }



    @GetMapping("/refreshToken")
    public void refreshToken(HttpServletRequest request , HttpServletResponse response) throws Exception{
        String authToken = request.getHeader(JWTUtil.AUTH_HEADER);
        if(authToken != null && authToken.startsWith(JWTUtil.PREFIX_HEADER)){
            try{
                //ignorer Bearer
                String jwt  = authToken.substring(JWTUtil.PREFIX_HEADER.length());
                //meme secret pour generate signature ici pour verifier token
                Algorithm algorithm = Algorithm.HMAC256(JWTUtil.SECRET);
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
                String email = decodedJWT.getSubject();
                User user = iUserService.laodUserByUserName(email);
                //génerer nouveau access token
                String jwtAccessToken = JWT.create()
                        .withSubject(user.getEmail())
                        //sexpire dans 5min en millissecondes
                        .withExpiresAt(new Date(System.currentTimeMillis()+JWTUtil.EXP_ACCESS_TOKEN))
                        //nom de l'app qui a gérer le token (on va mettre url de la requete)
                        .withIssuer(request.getRequestURL().toString())
                        //convertit la liste des authoritys de lobjet user spring en liste de priviléges
                        .withClaim("privileges",user.getRole().getPrivileges().stream().map(Privilege::getPrivilegeName).collect(Collectors.toList()))
                        .withClaim("role",user.getRole().getRoleName())
                        .sign(algorithm);

                Map<String , String> idToken = new HashMap<>();
                idToken.put("accessToken",jwtAccessToken);
                idToken.put("refreshToken",jwt);
                response.setHeader(JWTUtil.AUTH_HEADER,jwtAccessToken);

                response.setContentType("application/json");
                //pour serialiser un objet en json avec entrer et sortie
                new ObjectMapper().writeValue(response.getOutputStream(),idToken);

            }catch (Exception e){
                throw e;
            }
        }else {
            throw  new RuntimeException("Refresh Token Required");
        }


    }

    @GetMapping("/currentUser")
    public User profile(Principal principal){
        //Principal utilsateur username
        return iUserService.laodUserByUserName(principal.getName());

    }


    @GetMapping("/activation")
    public  String activation(@RequestParam("token") String token ){

        VerificationToken verificationToken = verificationTokenService.findByToken(token);
        if (verificationToken == null){
            return "Token cerification invalid";
        }else {
            User user = verificationToken.getUser();
            // if user in not activated
            if(!user.isEnabled()){
                //current Timestamp
                Timestamp cuurentTimesTamp = new Timestamp(System.currentTimeMillis());
                // check if the token is expired
                if(verificationToken.getExpiryDate().before(cuurentTimesTamp)){
                    return "Your verification token has expired !";
                }else {
                    user.setEnabled(true);

                    iUserService.updateUser(user);
                    return "Your account is successfuly activated";
                }
            }else {
                return "Your account is already activated";
            }
        }
    }




    @GetMapping("/getListPrivilegesUser/{idUser}")
    public Set<Privilege> getListPrivilegesUser(@PathVariable("idUser")int idUser) {
        return  iUserService.getListPrivilegesUser(idUser);
    }
    @PostMapping("/annulerPrivilegeUser/{idUser}/{idPrivilege}")
    public User annulerPrivilegeUser(@PathVariable("idUser")int idUser,@PathVariable("idPrivilege") int idPrivilege) {
        return iUserService.annulerPrivilegeUser(idUser,idPrivilege);

    }

    @PostMapping("/addPrivilegeToUser/{idUser}/{idPrivilegr}")
    public User addPrivilegeToUser(@PathVariable("idUser")int idUser,@PathVariable("idPrivilegr")int idPrivilegr) {
        return iUserService.addPrivilegeToUser( idUser,idPrivilegr);
    }







    /**************************************************************************************************/
    @PostMapping("/AffectRoleToPrivilege/{idRole}/{idPrivilege}")
    public Boolean AffectRoleToPrivilege(@PathVariable("idRole") int idRole ,@PathVariable("idPrivilege") int idPrivilege){
        return iRoleService.AffectRoleToPrivilege(idRole,idPrivilege);
    }
    @PostMapping("/addRoleAddRoleWithPrivilege")
    public Role AddRoleWithPrivilege(@RequestBody Role role) {

        return iRoleService.AddRoleWithPrivilege(role);
    }
    @GetMapping("/AllRoles")
    @ResponseBody
    public List<Role> getAllRoles(){
        return iRoleService.getAllRoles();
    }

    @PostMapping("/addRole")
    @ResponseBody
    public Role addRole (@RequestBody Role role){
        return iRoleService.addRole(role);
    }

    @GetMapping("/getRoleById/{id}")
    @ResponseBody
    public Role  getRoleById(@PathVariable("id") int id){
        return   iRoleService.getRoleById(id);
    }


    @DeleteMapping("/deleteRole/{id}")
    private void deleteRole(@PathVariable("id") int id)
    {
        iRoleService.deleteRole(id);
    }

    @PutMapping("/updateRole")
    private Role updateRole(@RequestBody Role role)
    {
        iRoleService.updateRole(role);
        return role;
    }





    /**************************************************************************************************/
    @GetMapping("/AllPrivileges")
    @ResponseBody
    public List<Privilege> getAllAllPrivileges(){
        return iPrivilegeService.getAllPrivileges();
    }

    @PostMapping("/addPrivilege")
    @ResponseBody
    public Privilege addPrivilege (@RequestBody Privilege privilege){
        return iPrivilegeService.addPrivilege(privilege);
    }

    @GetMapping("/getPrivilegeById/{id}")
    @ResponseBody
    public Privilege getaddPrivilegeById(@PathVariable("id") int id){
        return   iPrivilegeService.getPrivilegeById(id);
    }


    @DeleteMapping("/deletePrivilege/{id}")
    private void deletegetaddPrivilege(@PathVariable("id") int id)
    {
        iPrivilegeService.deletePrivilege(id);
    }

    @PutMapping("/updatePrivilege")
    private Privilege updatePrivilege(@RequestBody Privilege privilege)
    {
        iPrivilegeService.updatePrivilege(privilege);
        return privilege;
    }

    @PostMapping("/addAndAssignPrivilegeToRole/{idRole}")
    public Privilege addAndAssignPrivilegeToRole(@RequestBody Privilege privilege, @PathVariable("idRole") int idRole) {
        return iPrivilegeService.addAndAssignPrivilegeToRole(privilege,idRole);
    }










    @GetMapping("/laodUserByUserName/{email}")
    public User laodUserByUserName(@PathVariable("email")String email){
        return iUserService.laodUserByUserName(email);
    }



    private static String authorizationRequestBaseUri
            = "oauth2/authorization";
    Map<String, String> oauth2AuthenticationUrls
            = new HashMap<>();


    private ClientRegistrationRepository clientRegistrationRepository;

    @GetMapping("/oauth_login")
    public String getLoginPage(Model model) {
        // ...

        return "oauth_login";
    }

    @GetMapping("/getListUsersByIdRole/{id}")
    public List<User> getListUsersByIdRole(@PathVariable("id") int idRole) {
        return iRoleService.getListUsersByIdRole(idRole);
    }
    @GetMapping("/getListPrivilegesByIdRole/{id}")
    public List<Privilege> getListPrivilégesByIdRole(@PathVariable("id")int idRole) {
        return iRoleService.getListPrivilégesByIdRole(idRole);
    }
    @GetMapping("/ListRoleByIdPrivilege/{id}")
    public List<Role> ListRoleByIdPrivilege(@PathVariable("id")int idPrivilege) {
        return iPrivilegeService.ListRoleByIdPrivilege(idPrivilege) ;
    }

}
