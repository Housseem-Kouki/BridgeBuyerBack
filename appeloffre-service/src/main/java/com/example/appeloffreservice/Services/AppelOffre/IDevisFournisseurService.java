package com.example.appeloffreservice.Services.AppelOffre;

import com.example.appeloffreservice.Entities.DevisFourniseur;

import java.util.List;

public interface IDevisFournisseurService {
    public DevisFourniseur addDevisFourniseur(DevisFourniseur devisFourniseur);
    public DevisFourniseur updateDevisFourniseur(DevisFourniseur devisFourniseur);
    public void deleteDevisFourniseur(int id);
    public DevisFourniseur getDevisFourniseurbyId(int id);
    public List<DevisFourniseur> getAllDevisFourniseur();
    public void assignAppeloffreAndUserToDevis( int idAppeloffre ,int idDevis, int idUser);

    public DevisFourniseur comparerDevis(int id1 , int id2 );
    public double calculremise(int id1);

}
