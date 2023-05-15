package com.example.userservice.DataMining;


import com.example.userservice.Entities.*;
import com.example.userservice.Repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class DataGeneratorController{
    BonRetourRepository bonRetourRepository;
    DataGeneratorBonRetour dataGeneratorBonRetour;
BonReceptionRepository bonReceptionRepository;
DataGeneratorBonReception dataGeneratorBonReception;
    LivraisonRepository livraisonRepository;
    DataGeneratorLivraision dataGeneratorLivraision;
DataGeneratorChargeFinance dataGeneratorChargeFinance;
ChargeFinanciereRepository chargeFinanciereRepository;
    DataGenerator dataGenerator;
    DataGeneratorEmplacement dataGeneratorEmplacement;
    DataGeneratorDemandeAchat dataGeneratorDemandeAchat;
    DataGeneratorArticle dataGeneratorArticle;
    UserRepository userRepository;
    OffreRepository offreRepository;
    DataGeneratorOffre dataGeneratorOffre;
    EmplacementRepository emplacementRepository;
    DemandeAchatRepository demandeAchatRepository;
    AppelOffreRepository appelOffreRepository;
    ArticleRepository articleRepository;
    DevisFourniseurRepository devisFourniseurRepository;
    DataGeneratorDevisFournisseur dataGeneratorDevisFournisseur;

    PaimentRepository paimentRepository;
    DataGeneratorPayment dataGeneratorPayment;

    TaxeRepository taxeRepository;
    DataGeneratorTaxe dataGeneratorTaxe;
CommandeRepository commandeRepository;
DataGeneratorCommande dataGeneratorCommande;

    FactureRepository factureRepository;
    DataGeneratorFacture dataGeneratorFacture;
DataGeneratorAppelOffre dataGeneratorAppelOffre;
    @PostMapping("/dataGeneratorUser/{userNumber}")
    public String dataGeneratorUser(@PathVariable("userNumber") int userNumber) {
        // int userNumber = 2000;
        for (int i = 0; i < userNumber; i++) {
            User user =    dataGenerator.generate();
            // Set user properties here
            userRepository.save(user); // Enregistrer l'utilisateur dans la base de données
        }
        //  model.addAttribute("customer", user);
        return userNumber+" insérer dans la base avec success !";
    }


    @PostMapping("/dataGeneratorEmplacement/{number}")
    public String dataGeneratorEmplacement(@PathVariable("number") int number) {
        // int userNumber = 2000;
        for (int i = 0; i < number; i++) {
            Emplacement emplacement =    dataGeneratorEmplacement.generate();
            // Set user properties here
            emplacementRepository.save(emplacement); // Enregistrer l'utilisateur dans la base de données
        }
        //  model.addAttribute("customer", user);
        return number+" insérer dans la base avec success !";
    }

    @PostMapping("/dataDemandeAchat/{number}")
    public String dataDemandeAchat(@PathVariable("number") int number) {
        // int userNumber = 2000;
        for (int i = 0; i < number; i++) {
            DemandeAchat demandeAchat =    dataGeneratorDemandeAchat.generate();
            // Set user properties here
            demandeAchatRepository.save(demandeAchat); // Enregistrer l'utilisateur dans la base de données
        }
        //  model.addAttribute("customer", user);
        return number+" insérer dans la base avec success !";
    }

    @PostMapping("/dataArticle/{number}")
    public String dataArticle(@PathVariable("number") int number) {
        // int userNumber = 2000;
        for (int i = 0; i < number; i++) {
            Article article =    dataGeneratorArticle.generate();
            // Set user properties here
            try {
                articleRepository.save(article);
            }catch (Exception e){
                e.getMessage();
            }
           // Enregistrer l'utilisateur dans la base de données
        }
        //  model.addAttribute("customer", user);
        return number+" insérer dans la base avec success !";
    }



    @PostMapping("/dataAppelOffre/{number}")
    public String dataAppelOffre(@PathVariable("number") int number) {
        // int userNumber = 2000;
        for (int i = 0; i < number; i++) {
            AppelOffre appelOffre =    dataGeneratorAppelOffre.generate();
            // Set user properties here
            try {
                appelOffreRepository.save(appelOffre);
            }catch (Exception e){
                e.getMessage();
            }
            // Enregistrer l'utilisateur dans la base de données
        }
        //  model.addAttribute("customer", user);
        return number+" insérer dans la base avec success !";
    }


    @PostMapping("/dataoffre/{number}")
    public String dataoffre(@PathVariable("number") int number) {
        // int userNumber = 2000;
        for (int i = 0; i < number; i++) {
            Offre offre =    dataGeneratorOffre.generate();
            // Set user properties here
            offreRepository.save(offre); // Enregistrer l'utilisateur dans la base de données
        }
        //  model.addAttribute("customer", user);
        return number+" insérer dans la base avec success !";
    }

    @PostMapping("/dataDevisF/{number}")
    public String dataDevisF(@PathVariable("number") int number) {
        // int userNumber = 2000;
        for (int i = 0; i < number; i++) {
            DevisFourniseur devisFourniseur =    dataGeneratorDevisFournisseur.generate();
            // Set user properties here
            devisFourniseurRepository.save(devisFourniseur); // Enregistrer l'utilisateur dans la base de données
        }
        //  model.addAttribute("customer", user);
        return number+" insérer dans la base avec success !";
    }

    @PostMapping("/payment/{number}")
    public String payment(@PathVariable("number") int number) {
        // int userNumber = 2000;
        for (int i = 0; i < number; i++) {
            Paiment paiment =    dataGeneratorPayment.generate();
            // Set user properties here
            paimentRepository.save(paiment); // Enregistrer l'utilisateur dans la base de données
        }
        //  model.addAttribute("customer", user);
        return number+" insérer dans la base avec success !";
    }


    @PostMapping("/taxe/{number}")
    public String taxe(@PathVariable("number") int number) {
        // int userNumber = 2000;
        for (int i = 0; i < number; i++) {
            Taxe taxe =    dataGeneratorTaxe.generate();
            // Set user properties here
            taxeRepository.save(taxe); // Enregistrer l'utilisateur dans la base de données
        }
        //  model.addAttribute("customer", user);
        return number+" insérer dans la base avec success !";
    }

    @PostMapping("/charge/{number}")
    public String charge(@PathVariable("number") int number) {
        // int userNumber = 2000;
        for (int i = 0; i < number; i++) {
            ChargeFinanciere chargeFinanciere =    dataGeneratorChargeFinance.generate();
            // Set user properties here
            chargeFinanciereRepository.save(chargeFinanciere); // Enregistrer l'utilisateur dans la base de données
        }
        //  model.addAttribute("customer", user);
        return number+" insérer dans la base avec success !";
    }

    @PostMapping("/facture/{number}")
    public String facture(@PathVariable("number") int number) {
        // int userNumber = 2000;
        for (int i = 0; i < number; i++) {
            Facture facture =    dataGeneratorFacture.generate();
            try {

                // Set user properties here
                factureRepository.save(facture);
            }catch (Exception e){
        e.getMessage();
            }
        // Enregistrer l'utilisateur dans la base de données
        }
        //  model.addAttribute("customer", user);
        return number+" insérer dans la base avec success !";
    }

    @PostMapping("/commande/{number}")
    public String commande(@PathVariable("number") int number) {
        // int userNumber = 2000;
        for (int i = 0; i < number; i++) {
           Commande commande = new Commande();
            try {
                 commande =    dataGeneratorCommande.generate();
                commandeRepository.save(commande);
            }catch (Exception e){
                e.getMessage();
            }
            // Set user properties here
            commandeRepository.save(commande); // Enregistrer l'utilisateur dans la base de données
        }
        //  model.addAttribute("customer", user);
        return number+" insérer dans la base avec success !";
    }

    @PostMapping("/livraision/{number}")
    public String livraision(@PathVariable("number") int number) {
        // int userNumber = 2000;
        for (int i = 0; i < number; i++) {
            Livraison livraison =    dataGeneratorLivraision.generate();
            // Set user properties here
            livraisonRepository.save(livraison); // Enregistrer l'utilisateur dans la base de données
        }
        //  model.addAttribute("customer", user);
        return number+" insérer dans la base avec success !";
    }

    @PostMapping("/bonReception/{number}")
    public String bonReception(@PathVariable("number") int number) {
        // int userNumber = 2000;
        for (int i = 0; i < number; i++) {
            BonReception bonReception =    dataGeneratorBonReception.generate();
            // Set user properties here
            bonReceptionRepository.save(bonReception); // Enregistrer l'utilisateur dans la base de données
        }
        //  model.addAttribute("customer", user);
        return number+" insérer dans la base avec success !";
    }


    @PostMapping("/bonRetour/{number}")
    public String bonRetour(@PathVariable("number") int number) {
        // int userNumber = 2000;
        for (int i = 0; i < number; i++) {
            BonRetour bonRetour =    dataGeneratorBonRetour.generate();
            // Set user properties here

            bonRetourRepository.save(bonRetour); // Enregistrer l'utilisateur dans la base de données
        }
        //  model.addAttribute("customer", user);
        return number+" insérer dans la base avec success !";
    }
}
