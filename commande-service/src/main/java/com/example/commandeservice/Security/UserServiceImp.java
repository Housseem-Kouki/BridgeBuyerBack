package com.example.commandeservice.Security;


import com.example.commandeservice.Entities.User;
import com.example.commandeservice.Repository.RoleRepository;
import com.example.commandeservice.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImp implements IUserService {

    UserRepository userRepository;
    RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public User laodUserByUserName(String email) {
        return userRepository.findByEmail(email);
    }





}
