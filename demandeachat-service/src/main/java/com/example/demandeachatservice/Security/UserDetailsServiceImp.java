package com.example.demandeachatservice.Security;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailsServiceImp implements org.springframework.security.core.userdetails.UserDetailsService {
    private IUserService userService;

    public UserDetailsServiceImp(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

         com.example.demandeachatservice.Entities.User user = userService.laodUserByUserName(email);
        //user objet de spring
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        user.getRole().getPrivileges().forEach( p->{
                    authorities.add(new SimpleGrantedAuthority(p.getPrivilegeName()));
                }
        );
        return new User(user.getEmail(),user.getPassword() ,
                authorities);
    }
}
