package com.example.userservice.DataMining;

import com.example.userservice.Entities.ChargeFinanciere;
import com.example.userservice.Entities.Facture;
import com.example.userservice.Entities.Taxe;
import com.example.userservice.Repository.FactureRepository;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Component
@AllArgsConstructor
public class DataGeneratorChargeFinance {
    private final Faker faker = new Faker();
FactureRepository factureRepository;


    public ChargeFinanciere generate() {
        ChargeFinanciere chargeFinanciere = new ChargeFinanciere();

        chargeFinanciere.setMontantCharge((float) faker.number().randomDouble(2, 10, 100));
        chargeFinanciere.setNomCharge( faker.company().buzzword());
        Random random = new Random();
        List<Integer> idsF = factureRepository.listdesIds();
        int randomIndexCh = random.nextInt(idsF.size());
        int randomIdch = idsF.get(randomIndexCh);
        Facture facture = factureRepository.findById(randomIdch).orElse(null);
        Set<Facture> factures = new HashSet<>();
        factures.add(facture);
        chargeFinanciere.setFactures(factures);

        return chargeFinanciere;
    }




}
