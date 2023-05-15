package com.example.userservice.DataMining;

import com.example.userservice.Entities.*;
import com.example.userservice.Repository.AppelOffreRepository;
import com.example.userservice.Repository.EmplacementRepository;
import com.example.userservice.Repository.UserRepository;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Component
@AllArgsConstructor
public class DataGeneratorDevisFournisseur {

    private final Faker faker = new Faker();
    UserRepository userRepository;
    AppelOffreRepository appelOffreRepository;
    public DevisFourniseur generate() {
       DevisFourniseur devisFourniseur = new DevisFourniseur();
        devisFourniseur.setPTSansRemise((double) faker.number().randomDouble(2, 1, 1000));
        devisFourniseur.setPtotal((double) faker.number().randomDouble(2, 1, 1000));
        devisFourniseur.setCommentaire(faker.lorem().sentence());
        devisFourniseur.setDelaiLivraison(faker.number().numberBetween(1,60));
        devisFourniseur.setDisopnible(faker.bool().bool());
        devisFourniseur.setPeriodeValidite(faker.number().numberBetween(1,60));
        devisFourniseur.setPourcentageRemise((float) faker.number().randomDouble(2, 1, 100));
        devisFourniseur.setPrixInitiale((float) faker.number().randomDouble(2, 1, 1000));
        devisFourniseur.setRemise((int) faker.number().randomDouble(2, 1, 1000));


        List<Integer> ids = userRepository.listdesIds();
        Random random = new Random();
        int randomIndex = random.nextInt(ids.size());
        int randomId = ids.get(randomIndex);
        User fournisseur = userRepository.findById(randomId).orElse(null);
        devisFourniseur.setFourniseur(fournisseur);

        List<Integer> idsAppelsOffre = appelOffreRepository.listdesIds();
        int randomIndex2 = random.nextInt(idsAppelsOffre.size());
        int randomId2 = idsAppelsOffre.get(randomIndex2);
        AppelOffre appelOffre = appelOffreRepository.findById(randomId2).orElse(null);
        devisFourniseur.setAppelOffre(appelOffre);

       return devisFourniseur;
    }


}
