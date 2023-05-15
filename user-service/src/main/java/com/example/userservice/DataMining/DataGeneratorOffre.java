package com.example.userservice.DataMining;

import com.example.userservice.Entities.*;
import com.example.userservice.Repository.DemandeAchatRepository;
import com.example.userservice.Repository.OffreRepository;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
@AllArgsConstructor
public class DataGeneratorOffre {
    private final Faker faker = new Faker();
    OffreRepository offreRepository;
    public Offre generate() {
        Offre offre = new Offre();
        offre.setCommentaire(faker.lorem().sentence());

        Date dateValidation = faker.date().future(30, TimeUnit.DAYS);
        offre.setDateValidation(dateValidation);

        Date datecreationO = faker.date().future(30, TimeUnit.DAYS);
        offre.setDatecreationO(datecreationO);
        offre.setEtat(faker.random().nextInt(0, 1));
        offre.setPrixOffre((double) faker.number().randomDouble(2, 1, 1000));

        return offre;
    }




}
