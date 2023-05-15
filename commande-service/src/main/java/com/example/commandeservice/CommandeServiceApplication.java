package com.example.commandeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableFeignClients
public class    CommandeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommandeServiceApplication.class, args);
    }
    @Bean
    PasswordEncoder passwordEncoder (){
        return new BCryptPasswordEncoder();
    }
}
