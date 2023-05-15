package com.example.userservice.Services.User;


import com.example.userservice.Entities.Privilege;
import com.example.userservice.Entities.Role;
import com.example.userservice.Entities.User;
import com.example.userservice.Entities.VerificationToken;
import com.example.userservice.Repository.PrivilegeRepository;
import com.example.userservice.Repository.RoleRepository;
import com.example.userservice.Repository.UserRepository;
import com.example.userservice.Repository.VerificationTokenRepository;
import com.example.userservice.Security.JWTUtil;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.nio.file.AccessDeniedException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class UserServiceImp implements IUserService {

    UserRepository userRepository;
    RoleRepository roleRepository;
    PrivilegeRepository privilegeRepository;
    VerificationTokenRepository verificationTokenRepository;
    private PasswordEncoder passwordEncoder;
    private VerificationTokenService verificationTokenService;
   private EmailUserService emailUserService;
    @Override
    public User addUser(User u) {
        String pwd = u.getPassword();
        u.setPassword(passwordEncoder.encode(pwd));
        u.setEnabled(false);
        u.setCreatedAt(Calendar.getInstance().getTime());
        Role r = u.getRole();
        Set<Privilege> affectedPrivileges = new HashSet<>();

        for (Privilege p : r.getPrivileges()) {
            Privilege privilege = privilegeRepository.findById(p.getIdPrivilege()).orElse(null);
            if (privilege != null) {
                if (privilege.getRoles() == null) {
                    privilege.setRoles(new HashSet<>());
                }
                privilege.getRoles().add(r);
                affectedPrivileges.add(privilege);
            }
        }

        r.setPrivileges(affectedPrivileges);
        roleRepository.save(r);
        try {
            String token = UUID.randomUUID().toString();
            verificationTokenService.affectUserToken(u , token);
            //send Email
            emailUserService.sendHtmlMail(u);

        }catch (Exception e){

        }


        return userRepository.save(u);
    }

    @Override
    public User updateUser(User u) {
        String pwd = u.getPassword();
        u.setPassword(passwordEncoder.encode(pwd));
        return userRepository.save(u);
    }

    @Override
    public void deleteUser(int id) {
        User user = userRepository.findById(id).orElse(null);
        System.out.println("ahouaaaaaaaaaaaaaaaaaaa"+user.getEmail());

      userRepository.deleteById(id);
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> getAllUsers() {

      //  User user = (User) authentication.getPrincipal();
      //  System.out.println("$$$$$user connected $$$$$$$ "+user.getEmail()+" id "+user.getIdUser());

        return userRepository.findAll();
    }

    @Override
    public boolean desActiverCompteUser(int idUser) {
        User user = userRepository.findById(idUser).orElse(null);
        if(user.isEnabled()){
            user.setEnabled(false);
            userRepository.save(user);
            return false;
        }else {
            user.setEnabled(true);
            userRepository.save(user);
            return true;
        }

    }

    @Override
    public User affecterUserRole(int idUser, int idRole) {
        User user = userRepository.findById(idUser).orElse(null);
        Role role = roleRepository.findById(idRole).orElse(null);
        user.setRole(role);
        userRepository.save(user);
        return user;
    }

    @Override
    public User laodUserByUserName(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public User addUserWithRoleAndAffectPrivileges(User u) {
        String pwd = u.getPassword();
        u.setPassword(passwordEncoder.encode(pwd));
        u.setEnabled(false);

        Role r = u.getRole();
        Set<Privilege> affectedPrivileges = new HashSet<>();

        for (Privilege p : r.getPrivileges()) {
            Privilege privilege = privilegeRepository.findById(p.getIdPrivilege()).orElse(null);
            if (privilege != null) {
                if (privilege.getRoles() == null) {
                    privilege.setRoles(new HashSet<>());
                }
                privilege.getRoles().add(r);
                affectedPrivileges.add(privilege);
            }
        }

        r.setPrivileges(affectedPrivileges);
        roleRepository.save(r);
        try {
            String token = UUID.randomUUID().toString();
            verificationTokenService.affectUserToken(u , token);
            //send Email
            emailUserService.sendHtmlMail(u);

        }catch (Exception e){
            throw new RuntimeException("email invalid");
        }
        return userRepository.save(u);
    }

    @Override
    public Response requestPasswordReset(String email) throws Exception {
        User user = userRepository.findByEmail(email);

        if(user==null) {
           return Response.status(Response.Status.NOT_FOUND).entity("email n'est pas disponible !").build();

        }

       try {
           String token = UUID.randomUUID().toString();
           verificationTokenService.affectUserToken(user , token);

           emailUserService.resetPasswordMail(user);

       }catch (Exception e){
           throw new RuntimeException("email invalid");
       }
       return Response.status(Response.Status.OK).entity("email de mot de passe oublié a été envoyé ").build();
    }



    @Override
    public Response resetPassword(String token, String NewPassword , String ConfirmPassword) {
        VerificationToken verificationToken = verificationTokenService.findByToken(token);
        if (!(NewPassword.equals(ConfirmPassword))){
            return  Response.status(Response.Status.BAD_REQUEST).entity("Les deux mots de passe ne correspondent pas. Veuillez réessayer. ").build();
        }
        Timestamp cuurentTimesTamp = new Timestamp(System.currentTimeMillis());
        // check if the token is expired
        if(verificationToken.getExpiryDate().before(cuurentTimesTamp)) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Lien expiré . Veuillez renvoyer votre demande  ").build();

        }
        if (verificationToken==null){
            return  Response.status(Response.Status.BAD_REQUEST).entity("Lien erroné . Veuillez verifier le lien de modification   ").build();
        }
        User user = userRepository.findById(verificationToken.getUser().getIdUser()).orElse(null);
        //prepare new password
        String encodedPassword = passwordEncoder.encode(NewPassword);
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return  Response.status(Response.Status.OK).entity("Mot de passe a été modifier  ").build();
    }


    @Override
    public Set<Privilege> getListPrivilegesUser(int idUser) {
        User user = userRepository.findById(idUser).orElse(null);
        //  List<Privilege> ls = (List<Privilege>) user.getRole().getPrivileges();
        return user.getRole().getPrivileges();
    }

    @Override
    @Transactional
    public User annulerPrivilegeUser(int idUser, int idPrivilege) {
        User user = userRepository.findById(idUser).orElse(null);
        Privilege privilege = privilegeRepository.findById(idPrivilege).orElse(null);
        Role role = user.getRole();
        role.getPrivileges().remove(privilege);
        privilege.getRoles().remove(role);
        privilegeRepository.save(privilege);
        roleRepository.save(role);
        return userRepository.save(user);
    }

    @Override
    public User addPrivilegeToUser(int idUser, int idPrivilegr) {

        User user = userRepository.findById(idUser).orElse(null);

        Privilege privilege = privilegeRepository.findById(idPrivilegr).orElse(null);
       user.getRole().getPrivileges().add(privilege);
       privilege.getRoles().add(user.getRole());
        privilegeRepository.save(privilege);
        return userRepository.save(user);
    }

}
