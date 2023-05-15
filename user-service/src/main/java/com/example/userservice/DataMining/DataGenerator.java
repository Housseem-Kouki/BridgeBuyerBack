package com.example.userservice.DataMining;

import com.example.userservice.Entities.Emplacement;
import com.example.userservice.Entities.Role;
import com.example.userservice.Entities.User;
import com.example.userservice.Repository.EmplacementRepository;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@AllArgsConstructor
public class DataGenerator {
    EmplacementRepository emplacementRepository;
    private final Faker faker = new Faker();
    public User generate() {
      //  List<Integer> ids = emplacementRepository.listdesIds();


        User user = new User();
        user.setEmail(faker.internet().emailAddress());
        user.setFname(faker.name().firstName());
        user.setLname(faker.name().lastName());
        user.setPassword(faker.internet().password());
        user.setEnabled(faker.bool().bool());
        user.setPhoneNumber(faker.phoneNumber().phoneNumber());
        // Générer aléatoirement un rôle
       Role role = new Role();
       role.setIdRole(faker.number().numberBetween(1, 4));
       user.setRole(role);

        // générer aléatoirement un EMPLACEMENT
        List<Integer> ids = emplacementRepository.listdesIds();

        Random random = new Random();
        int randomIndex = random.nextInt(ids.size());
        int randomId = ids.get(randomIndex);

        Emplacement emplacement = emplacementRepository.findById(randomId).orElse(null);
       // System.out.println("random emplacement : "+ emplacement.getNomEmplacement() + emplacement.getIdEmplacement());
        user.setEmplacement(emplacement);
        return user;
    }

}
