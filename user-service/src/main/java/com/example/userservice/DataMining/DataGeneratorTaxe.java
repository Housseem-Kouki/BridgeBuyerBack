package com.example.userservice.DataMining;

import com.example.userservice.Entities.*;
import com.example.userservice.Repository.DemandeAchatRepository;
import com.example.userservice.Repository.FactureRepository;
import com.example.userservice.Repository.OffreRepository;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
@AllArgsConstructor
public class DataGeneratorTaxe {
    private final Faker faker = new Faker();

FactureRepository factureRepository;

    public Taxe generate() {
        Taxe tax = new Taxe();
        Random random = new Random();
        tax.setNomTaxe(  faker.numerify("Tax-###"));
        tax.setTauxTaxe(random.nextInt(20));


        List<Integer> idsF = factureRepository.listdesIds();
        int randomIndexCh = random.nextInt(idsF.size());
        int randomIdch = idsF.get(randomIndexCh);
        Facture facture = factureRepository.findById(randomIdch).orElse(null);
        Set<Facture> factures = new HashSet<>();
        factures.add(facture);
        tax.setFactures(factures);



        return tax;
    }




}
