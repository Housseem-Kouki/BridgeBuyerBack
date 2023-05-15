package com.example.userservice.DataMining;

import com.example.userservice.Entities.*;
import com.example.userservice.Repository.*;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
@AllArgsConstructor
public class DataGeneratorLivraision {
    private final Faker faker = new Faker();
    OffreRepository offreRepository;
    PaimentRepository paimentRepository;
    LivraisonRepository livraisonRepository;
    FactureRepository factureRepository;
    BonReceptionRepository bonReceptionRepository;
    CommandeRepository commandeRepository;
    public Livraison generate() {
        Livraison livraison = new Livraison();
        livraison.setArchive(faker.bool().bool());

        Date datePay = faker.date().future(30, TimeUnit.DAYS);
        livraison.setDateLivraison(datePay);

        String etat = faker.options().option("recu", "non recu", "partiellement recu");
        livraison.setEtat(etat);

        livraison.setQuantiteDelivre(faker.number().numberBetween(0, 200));

        //Commande
        Random random = new Random();
        List<Integer> idsCmd = commandeRepository.listdesIds();
        int randomIndex = random.nextInt(idsCmd.size());
        int randomIdoff = idsCmd.get(randomIndex);
        Commande commande = commandeRepository.findById(randomIdoff).orElse(null);
        livraison.setCommande(commande);


        //Bonreception
        List<Integer> idsb = commandeRepository.listdesIds();
        int randomIndexb = random.nextInt(idsb.size());
        int randomIdoffb = idsb.get(randomIndexb);
        BonReception bonReception = bonReceptionRepository.findById(randomIdoffb).orElse(null);
        livraison.setBonReception(bonReception);

        return livraison;
    }




}
