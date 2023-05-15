package com.example.commandeservice.ServiceCommande;



import com.example.commandeservice.Entities.Commande;

import java.security.Principal;
import java.util.List;

public interface ICommandeService {

    public List<Commande> getAllCommandes();

    public Commande updateCommande(Commande commande);
    public void deleteCommande(int id);
    public Commande getCommandeById(int id );

    public List<Commande> getCommandeByUser(int id);
    public List<Commande> getCommandeFournisuer(Principal principal);

    public List<Commande> getCommandeByEtat(int etat,Principal principal);
    public Commande addCommandeAndAssignOffre(Commande commande, int id);
    public Commande annulerCommande(int id);


    Commande reactiver(int idCommande);
}