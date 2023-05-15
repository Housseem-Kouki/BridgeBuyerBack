package com.example.userservice.DataMining;

import com.example.userservice.Entities.ChargeFinanciere;
import com.example.userservice.Entities.Commande;
import com.example.userservice.Entities.Facture;
import com.example.userservice.Entities.Taxe;
import com.example.userservice.Repository.ChargeFinanciereRepository;
import com.example.userservice.Repository.CommandeRepository;
import com.example.userservice.Repository.FactureRepository;
import com.example.userservice.Repository.TaxeRepository;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
@AllArgsConstructor
public class DataGeneratorFacture {
    private final Faker faker = new Faker();
    CommandeRepository commandeRepository;
    FactureRepository factureRepository;
    TaxeRepository taxeRepository;
    ChargeFinanciereRepository chargeFinanciereRepository;


    public Facture generate() {
        Facture facture = new Facture();
        Random random = new Random();
        facture.setArchive(faker.bool().bool());

        Date datePay = faker.date().future(30, TimeUnit.DAYS);
        facture.setDateFacture(datePay);

        facture.setDescription(faker.lorem().sentence());
        facture.setMontantFacture((float) faker.number().randomDouble(2, 1, 1000));


        //liste des id de de    mande d'achat
        List<Integer> idsCom = commandeRepository.listdesIds();
        int randomIndex = random.nextInt(idsCom.size());
        int randomIdCmd = idsCom.get(randomIndex);
        Commande cmd = commandeRepository.findById(randomIdCmd).orElse(null);
        facture.setCommande(cmd);

        List<Integer> idsCharges = chargeFinanciereRepository.listdesIds();
        int randomIndexCh = random.nextInt(idsCharges.size());
        int randomIdch = idsCharges.get(randomIndexCh);
        ChargeFinanciere chargeFinanciere = chargeFinanciereRepository.findById(randomIdch).orElse(null);
        Set<ChargeFinanciere> chargeFinancieres = new HashSet<>();
        chargeFinancieres.add(chargeFinanciere);
        facture.setListChargeFinancieres(chargeFinancieres);

        List<Integer> idsTaxes = taxeRepository.listdesIds();
        int randomIndexTax = random.nextInt(idsTaxes.size());
        int randomIdtax = idsTaxes.get(randomIndexTax);
        Taxe taxe = taxeRepository.findById(randomIdtax).orElse(null);
        Set<Taxe> taxes = new HashSet<>();
        taxes.add(taxe);
        facture.setTaxes(taxes);
        return facture;
    }




}
