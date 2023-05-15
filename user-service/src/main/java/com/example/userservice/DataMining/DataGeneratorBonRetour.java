package com.example.userservice.DataMining;

import com.example.userservice.Entities.BonReception;
import com.example.userservice.Entities.BonRetour;
import com.example.userservice.Entities.FactureAvoir;
import com.example.userservice.Entities.Livraison;
import com.example.userservice.Repository.BonReceptionRepository;
import com.example.userservice.Repository.BonRetourRepository;
import com.example.userservice.Repository.FactureAvoirRepository;
import com.example.userservice.Repository.LivraisonRepository;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
@AllArgsConstructor
public class DataGeneratorBonRetour {

    BonReceptionRepository bonReceptionRepository;
    FactureAvoirRepository factureAvoirRepository;
    private final Faker faker = new Faker();
    public BonRetour generate() {
        BonRetour bonRetour = new BonRetour();
        bonRetour.setArchive(faker.bool().bool());
        bonRetour.setCommmentaire(faker.lorem().sentence());
        bonRetour.setQuantiteRefusee(faker.number().numberBetween(0, 200));

        //bonReception
        Random random = new Random();
        List<Integer> idsbr = bonReceptionRepository.listdesIds();
        int randomIndexbr = random.nextInt(idsbr.size());
        int randomIdpp = idsbr.get(randomIndexbr);
        BonReception bonReception = bonReceptionRepository.findById(randomIdpp).orElse(null);
        bonRetour.setBonReception(bonReception);




        return bonRetour;
    }
}
