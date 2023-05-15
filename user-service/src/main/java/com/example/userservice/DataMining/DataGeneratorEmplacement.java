package com.example.userservice.DataMining;

import com.example.userservice.Entities.*;
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
public class DataGeneratorEmplacement {
    private final Faker faker = new Faker();
    DataGenerator dataGeneratoruser;
    UserRepository userRepository;


    public Emplacement generate() {
        Emplacement emplacement = new Emplacement();

        // Générer aléatoirement un adresseExpedition
       AdresseExpedition adresseExpedition = new AdresseExpedition();
       adresseExpedition.setIdAdresseExpedition(faker.number().numberBetween(1, 3));
       emplacement.setAdresseExpedition(adresseExpedition);

        // générer aléatoirement un devise
        Devise devise = new Devise();
        devise.setIdDevise(faker.number().numberBetween(1, 4));
        emplacement.setDevise(devise);

        // générer aléatoirement un user
        List<Integer> ids = userRepository.listdesIds();
        Random random = new Random();
        int randomIndex = random.nextInt(ids.size());
        int randomId = ids.get(randomIndex);
        User userResponsable = userRepository.findById(randomId).orElse(null);
        emplacement.setResponsableEmplacement(userResponsable);
        emplacement.setNomEmplacement(faker.company().name());
        return emplacement;
    }




}
