package com.example.commandeservice.ServiceCommande;

import com.example.commandeservice.Entities.Commande;
import com.example.commandeservice.Entities.Offre;
import com.example.commandeservice.Repository.CommandeRepository;
import com.example.commandeservice.Repository.OffreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class CommandeServiceImp implements ICommandeService{


    CommandeRepository commandeRepository;
    OffreRepository offreRepository;
    @Override
    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }

    @Override
    public Commande addCommandeAndAssignOffre(Commande commande, int id) {

        Offre offre = offreRepository.findById(id).orElse(null);
        offre.setEtat(1); // etat accepter
        commande.setOffre(offre);


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
    public List<Commande> getCommandeByUser() {
        return commandeRepository.getCommandeByUser(2);
    }

    @Override
    public List<Commande> getCommandeFournisuer() {
        return commandeRepository.getCommandeFournisuer(1);
    }

    @Override
    public List<Commande> getCommandeByEtat(int etat) {

        List<Commande> c = commandeRepository.getCommandeFournisuer(1);
        List<Commande> ab = null;
        for (Commande cm : c) {
            if (cm.getEtatCommande()==etat) {
                ab.add(cm);
            }
        }
        return ab;
    }
// spring gateway / eurika /  microservice
    // conteneur docker desktop  // docker composser
    // cloud ghateway client /
    //eureka
    //image de springbot
}
