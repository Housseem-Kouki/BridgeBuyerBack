package com.example.userservice.DataMining;

import com.example.userservice.Entities.*;
import com.example.userservice.Entities.Currency;
import com.example.userservice.Repository.EmplacementRepository;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
@AllArgsConstructor
public class DataGeneratorPayment {

    private final Faker faker = new Faker();
    public Paiment generate() {

        Paiment paiment = new Paiment();
        paiment.setAmount((float) faker.number().randomDouble(2, 1, 100));
         Random random = new Random();
        paiment.setCurrency(Currency.values()[random.nextInt(Currency.values().length)]);
        Date datePay = faker.date().future(30, TimeUnit.DAYS);
        paiment.setDatePayment(datePay);
        paiment.setToken(faker.bothify("???-########"));

        return paiment;
    }


}
