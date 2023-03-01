package com.example.demandeachatservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DemandeachatServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemandeachatServiceApplication.class, args);
    }

}
