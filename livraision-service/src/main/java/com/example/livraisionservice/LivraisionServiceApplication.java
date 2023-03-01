package com.example.livraisionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class LivraisionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LivraisionServiceApplication.class, args);
    }

}
