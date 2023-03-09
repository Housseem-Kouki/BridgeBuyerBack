package com.example.emlacementservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableAspectJAutoProxy
@Slf4j
@SpringBootApplication
@EnableFeignClients
public class EmlacementServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmlacementServiceApplication.class, args);
    }
    @Bean
    PasswordEncoder passwordEncoder (){
        return new BCryptPasswordEncoder();
    }
}
