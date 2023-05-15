package com.example.commandeservice.ServiceCommande;

import com.example.commandeservice.Entities.Commande;
import com.example.commandeservice.Entities.Offre;
import com.example.commandeservice.Entities.User;
import com.example.commandeservice.Repository.CommandeRepository;
import com.example.commandeservice.Repository.OffreRepository;
import com.example.commandeservice.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Date;
import java.util.List;
@Service
@AllArgsConstructor
public class CommandeServiceImp implements ICommandeService{
    UserRepository userRepository;

    CommandeRepository commandeRepository;
    OffreRepository offreRepository;


    @Override
    // liste de tout les commande operateur
    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }

    @Override
    // Acheteur accepte l'offre et  creation d'une commande
    public Commande addCommandeAndAssignOffre(Commande commande, int id) {

        Offre offre = offreRepository.findById(id).orElse(null);
        commande.setPrixTotalHorsTaxe(offre.getPrixOffre());
        commande.setEtatCommande(0);
        commande.setCreationCommande(new Date());
        offre.setEtat(1); //  offre accepter
        commande.setOffre(offre);

        return commandeRepository.save(commande);
    }

    @Override
    public Commande annulerCommande(int id) {
        Commande commande=commandeRepository.findById(id).orElse(null);
        commande.setEtatCommande(4);

        return commandeRepository.save(commande);

    }

    @Override
    public Commande reactiver(int idCommande) {
        Commande commande=commandeRepository.findById(idCommande).orElse(null);
        if(commande.getPaiment()!=null ) {
            commande.setEtatCommande(2);
        }else if (commande.getFacture()!=null) {

            commande.setEtatCommande(1);
        }
        else
            commande.setEtatCommande(0);
        {


        }




        return commandeRepository.save(commande);
    }

    @Override
    public Commande updateCommande(Commande commande) {
        return  commandeRepository.save(commande);
    }

    @Override
    public void deleteCommande(int id) {


        commandeRepository.deleteById(id);
    }

    @Override
    public Commande getCommandeById(int id) {
        return commandeRepository.findById(id).orElse(null);
    }
    @Override
    public List<Commande> getCommandeByUser(int id ) {

        User user = userRepository.findById(id).orElse(null);

        return commandeRepository.getCommandeByUser(user.getIdUser());

    }

    @Override
    public List<Commande> getCommandeFournisuer(Principal principal) {
        User user = userRepository.findByEmail(principal.getName());
        return commandeRepository.getCommandeFournisuer(user.getIdUser());
    }

    @Override
    public List<Commande> getCommandeByEtat(int etat,Principal principal) {
        User user = userRepository.findByEmail(principal.getName());
        List<Commande> c = commandeRepository.getCommandeFournisuer(user.getIdUser());
        List<Commande> ab = null;
        for (Commande cm : c) {
            if (cm.getEtatCommande() == etat) {
                ab.add(cm);
            }
        }
        return ab;
    }

}