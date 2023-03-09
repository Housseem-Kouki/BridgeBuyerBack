package com.example.commandeservice.ServiceCommande;

import com.example.commandeservice.Entities.Facture;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface IFactureService {

    public List<Facture> getAllFactures();
    public Facture addFactureAndAssignToCommande(Facture f , int idcommande);

    public void export(HttpServletResponse response , int idFacture) throws IOException;

    public Facture updateFacture(Facture facture);
    public void deleteFacture(int id);
    public Facture getFactureById(int id );



}