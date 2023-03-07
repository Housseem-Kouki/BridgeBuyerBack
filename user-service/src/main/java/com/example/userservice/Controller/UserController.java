package com.example.userservice.Controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.userservice.Entities.Privilege;
import com.example.userservice.Entities.Role;
import com.example.userservice.Entities.User;
import com.example.userservice.Entities.VerificationToken;
import com.example.userservice.Model.PasswordResetModel;
import com.example.userservice.Security.JWTUtil;
import com.example.userservice.Services.Privilege.IPrivilegeService;
import com.example.userservice.Services.Role.IRoleService;
import com.example.userservice.Services.User.IUserService;
import com.example.userservice.Services.User.VerificationTokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor

public class UserController {
IUserService iUserService;
IPrivilegeService iPrivilegeService;
IRoleService iRoleService;

VerificationTokenService verificationTokenService;
    @GetMapping("/helloUser")
    public String Hello(){

        return "bills hello from microservices user !";

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
        return iUserService.getAllUsers();
    }


    @PostMapping("/addUser")
    @ResponseBody
    public Response addUser (@RequestBody User user){

        if (iUserService.laodUserByUserName(user.getEmail()) != null){
            return Response.status(Response.Status.CONFLICT).entity("email déja exist  ").build();
        }else {
            iUserService.addUser(user);
            return Response.status(Response.Status.CREATED).entity(user).build();
        }


    }

    @GetMapping("/getUserById/{id}")
    @ResponseBody
    public  Response  getUserById(@PathVariable("id") int id){

        User user = iUserService.getUserById(id);
        if (user == null){
            return Response.status(Response.Status.NOT_FOUND).entity("aucun user existe avec l'id : "+id).build();
        }else {
            return Response.status(Response.Status.OK).entity(user).build();

        }

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

    /**************************************************************************************************/
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


}
