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
public class DataGeneratorCommande {
    private final Faker faker = new Faker();
    OffreRepository offreRepository;
    PaimentRepository paimentRepository;
    LivraisonRepository livraisonRepository;
    FactureRepository factureRepository;
    public Commande generate() {
        Commande commande = new Commande();
        commande.setArchive(faker.bool().bool());

        Date datePay = faker.date().future(30, TimeUnit.DAYS);
        commande.setCreationCommande(datePay);

        commande.setEtatCommande(faker.number().numberBetween(0, 1));

        double prixTotalHorsTaxe = faker.number().randomDouble(2, 10, 100);
        double tauxTaxe = 0.2; // Taux de taxe de 20%
        double prixTotalAvecTaxe = prixTotalHorsTaxe * (1 + tauxTaxe);
        commande.setPrixTotalAvecTaxe(prixTotalAvecTaxe);
        commande.setPrixTotalHorsTaxe(prixTotalHorsTaxe);
        //offre
        Random random = new Random();
        List<Integer> idsOffre = offreRepository.listdesIds();
        int randomIndex = random.nextInt(idsOffre.size());
        int randomIdoff = idsOffre.get(randomIndex);
        Offre offre = offreRepository.findById(randomIdoff).orElse(null);
        commande.setOffre(offre);

        //Paiment

        List<Integer> idspayss = paimentRepository.listdesIds();
        int randomIndexP = random.nextInt(idspayss.size());
        int randomIdpp = idspayss.get(randomIndex);
        Paiment paiment = paimentRepository.findById(randomIdpp).orElse(null);
        commande.setPaiment(paiment);

        //facture
        /*
        List<Integer> idsF = factureRepository.listdesIds();
        int randomIndexf = random.nextInt(idsF.size());
        int randomFF = idsF.get(randomIndexf);
        Facture facture = factureRepository.findById(randomFF).orElse(null);
        commande.setFacture(facture);
*/



        return commande;
    }




}
