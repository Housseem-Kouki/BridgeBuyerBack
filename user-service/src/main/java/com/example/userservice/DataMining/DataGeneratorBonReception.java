package com.example.userservice.DataMining;

import com.example.userservice.Entities.*;
import com.example.userservice.Repository.BonReceptionRepository;
import com.example.userservice.Repository.BonRetourRepository;
import com.example.userservice.Repository.EmplacementRepository;
import com.example.userservice.Repository.LivraisonRepository;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
@AllArgsConstructor
public class DataGeneratorBonReception {
    LivraisonRepository livraisonRepository;
    BonRetourRepository bonRetourRepository;
    private final Faker faker = new Faker();
    public BonReception generate() {
      BonReception bonReception = new BonReception();
      bonReception.setArchive(faker.bool().bool());
        Date datePay = faker.date().future(30, TimeUnit.DAYS);
        bonReception.setDateReception(datePay);
        String etat = faker.options().option("recu", "non recu", "partiellement recu");
        bonReception.setEtat(etat);
        int qA = faker.number().numberBetween(0, 200);
        int qR = faker.number().numberBetween(0, 200);
        int qRec = qA+qR;
        bonReception.setQuantiteAccepte(qA);
        bonReception.setQuantiteRefuse(qR);
        bonReception.setQuantiteRecevoir(qRec);

Random random = new Random();
        List<Integer> idsliv = livraisonRepository.listdesIds();
        int randomIndexliv = random.nextInt(idsliv.size());
        int randomIdpp = idsliv.get(randomIndexliv);
        Livraison livraison = livraisonRepository.findById(randomIdpp).orElse(null);
        bonReception.setLivraison(livraison);

        return bonReception;
    }
}
