package com.example.emlacementservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EmlacementServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmlacementServiceApplication.class, args);
    }

}
