package com.example.commandeservice.ServiceCommande;



import com.example.commandeservice.Entities.Commande;

import java.util.List;

public interface ICommandeService {

    public List<Commande> getAllCommandes();
    public Commande addCommandeAndAssignOffre(Commande commande, int id);
    public Commande updateCommande(Commande commande);
    public void deleteCommande(int id);
    public Commande getCommandeById(int id );

    public List<Commande> getCommandeByUser();
    public List<Commande> getCommandeFournisuer();

    public List<Commande> getCommandeByEtat(int etat);





}
