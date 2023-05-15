package com.example.appeloffreservice.Services.AppelOffre;

import com.example.appeloffreservice.Entities.AppelOffre;
import com.example.appeloffreservice.Entities.Offre;

import java.util.List;

public interface IOffreService {
    public Offre addAndAssignOffreToAppel(Offre offre , int idA);
    public Offre updateOffre(Offre offre);
    public void deleteOffre(int id);
    public Offre getOffrebyId(int id);
    public List<Offre> getAllOffre();

    public List<Offre> getOffreByEtat(int etat);
    public void assignofferToAppelOffre(int idAppeloffre, int idOffer);
    public List<Offre> findByCriteria(String commentaire, double prixOffre);
    public Offre addoffre( Offre offre, int idDevisFourniseur);

}
