package com.example.commandeservice.ServiceCommande;

import com.example.commandeservice.Entities.Facture;

import java.util.List;

public interface IFactureService {

    public List<Facture> getAllFactures();
    public Facture addFactureAndAssignToCommande(Facture f, int idCommande);
    public Facture updateFacture(Facture facture);
    public void deleteFacture(int id);
    public Facture getFactureById(int id );

}
