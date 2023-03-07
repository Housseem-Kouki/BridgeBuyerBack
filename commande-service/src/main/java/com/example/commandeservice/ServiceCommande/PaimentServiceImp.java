package com.example.commandeservice.ServiceCommande;

import com.example.commandeservice.Entities.Commande;
import com.example.commandeservice.Entities.Paiment;
import com.example.commandeservice.Repository.CommandeRepository;
import com.example.commandeservice.Repository.PaimentRepository;
import com.stripe.Stripe;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service

public class PaimentServiceImp implements IPaimentService{
    @Autowired

    MailService mailService;
    @Autowired
    CommandeRepository commandeRepository;
    @Autowired
    PaimentRepository paimentRepository;

    @Value("${stripe.secretkey}")
    private String secretKey;
    @Override
    public List<Paiment> getAllPaiments() {
        return paimentRepository.findAll();
    }

    @Override
    public Paiment addPaiment(Paiment paymentRequest) {
        double prixTotal = 0;
        Stripe.apiKey = secretKey;
        String token = paymentRequest.getToken();
        Set<Commande> cmds = paymentRequest.getCommandes();
        paymentRequest.setCommandes(null);
        for (Commande cmd : cmds) {
         Commande commande = commandeRepository.findById(cmd.getIdCommande()).orElse(null);
         commande.setPaiment(paymentRequest);
             prixTotal += cmd.getPrixTotal();

        }
        int amount = (int) prixTotal;

        String description = paymentRequest.getDescription();
        String currency = paymentRequest.getCurrency();
         paymentRequest.setAmount(amount);
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("amount", amount);
            params.put("currency", currency);
            params.put("description", description);
            params.put("source", token);
            Charge charge = Charge.create(params);
            mailService.sendEmail("malek.soufi@esprit.tn", "Payement", "Commande Payer");
            return paimentRepository.save(paymentRequest);
        } catch (Exception e) {
            return null;
        }


    }

    @Override
    public Paiment updatePaiment(Paiment paiment) {
        return paimentRepository.save(paiment);
    }

    @Override
    public void deletePaiment(int id) {
    paimentRepository.deleteById(id);
    }

    @Override
    public Paiment getPaimentById(int id) {
        return paimentRepository.findById(id).orElse(null);
    }





}
