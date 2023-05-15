package com.example.userservice.DataMining;

import com.example.userservice.Entities.*;
import com.example.userservice.Repository.DemandeAchatRepository;
import com.example.userservice.Repository.OffreRepository;
import com.example.userservice.Repository.UserRepository;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
@AllArgsConstructor
public class DataGeneratorAppelOffre {
    private final Faker faker = new Faker();
    DataGenerator dataGeneratoruser;
    DemandeAchatRepository demandeAchatRepository;
OffreRepository offreRepository;

    public AppelOffre generate() {
        Random random = new Random();
        AppelOffre appelOffre = new AppelOffre();
        appelOffre.setCommentaire(faker.lorem().sentence());

// Générer une date aléatoire dans le futur
        Date dateLimite = faker.date().future(30, TimeUnit.DAYS);
// Vérifier si la date générée est bien dans le futur par rapport à la date d'aujourd'hui
        if (dateLimite.before(new Date())) {
            // Si la date générée est dans le passé, ajouter un jour pour la faire tomber dans le futur
            dateLimite = faker.date().future(31, TimeUnit.DAYS);
        }

// Assigner la date limite à votre objet appel d'offre
        appelOffre.setDateLimite(dateLimite);

        Date datePay = faker.date().future(30, TimeUnit.DAYS);
        appelOffre.setDatePayment(datePay);

        Date dateCrea = faker.date().future(30, TimeUnit.DAYS);
        appelOffre.setDateCreationC(dateCrea);

        appelOffre.setEtat(faker.random().nextInt(0, 1));

        appelOffre.setPrixInitiale((float) faker.number().randomDouble(2, 1, 1000));

        appelOffre.setReference(faker.lorem().sentence());




        //DemandeAchat demandeAchat = new DemandeAchat();
        //demandeAchat.setIddemandeachat(faker.number().numberBetween(1100 , 1110));
        //  DemandeAchat demandeAchat1 = demandeAchatRepository.findById(faker.number().numberBetween(1095 , 1100)).orElse(null);
        //liste des id de de    mande d'achat
        List<Integer> ids = demandeAchatRepository.listdesIds();
        // Créer une instance de Random

        int randomIndex = random.nextInt(ids.size());
        int randomId = ids.get(randomIndex);
        //List<DemandeAchat> list = demandeAchatRepository.findAllById(ids);

        DemandeAchat demandeAchat1 = demandeAchatRepository.findById(randomId).orElse(null);

        appelOffre.setDemandeAchat(demandeAchat1);


        List<Integer> idsOffre = offreRepository.listdesIds();

        int randomIndex1 = random.nextInt(idsOffre.size());
        int randomIdOffre = idsOffre.get(randomIndex1);
        Offre offre = offreRepository.findById(randomIdOffre).orElse(null);
        appelOffre.setOffre(offre);

        return appelOffre;
    }




}
