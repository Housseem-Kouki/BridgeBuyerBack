package com.example.userservice.Services.User;


import com.example.userservice.Entities.Privilege;
import com.example.userservice.Entities.Role;
import com.example.userservice.Entities.User;
import com.example.userservice.Repository.PrivilegeRepository;
import com.example.userservice.Repository.RoleRepository;
import com.example.userservice.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.*;

@Service
@AllArgsConstructor
public class UserServiceImp implements IUserService {

    UserRepository userRepository;
    RoleRepository roleRepository;
    PrivilegeRepository privilegeRepository;
    private PasswordEncoder passwordEncoder;
    private VerificationTokenService verificationTokenService;
   private EmailUserService emailUserService;
    @Override
    public User addUser(User u) {
        String pwd = u.getPassword();
        u.setPassword(passwordEncoder.encode(pwd));
        u.setEnabled(false);


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




}
