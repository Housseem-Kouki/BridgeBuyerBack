package com.example.appeloffreservice.Services.AppelOffre;

import com.example.appeloffreservice.Entities.DevisFourniseur;
import com.example.appeloffreservice.Entities.User;

import java.security.Principal;
import java.util.List;

public interface IDevisFournisseurService {
    public DevisFourniseur addDevisFourniseur(DevisFourniseur devisFourniseur);
    public DevisFourniseur updateDevisFourniseur(DevisFourniseur devisFourniseur);
    public void deleteDevisFourniseur(int id);
    public DevisFourniseur getDevisFourniseurbyId(int id);
    public List<DevisFourniseur> getAllDevisFourniseur();
    public void assignAppeloffreAndUserToDevis( int idAppeloffre ,int idDevis, int idUser);
    public DevisFourniseur addAndassignAppelAndUserToDevis(DevisFourniseur devisFourniseur , int idAppeloffre , Principal user);
    public DevisFourniseur addDevisAndassignAppelToDevis(DevisFourniseur devisFourniseur , int idAppeloffre , int idUser );

    public DevisFourniseur comparerDevis(int id1 , int id2 );
    public float calculremise(int id1 ,int idA, float pourcentageRemise);

    public List<DevisFourniseur> getDevisFournisseur(int idFournisseur);

    public  DevisFourniseur updateTotalDevis(int idDevis);
    public DevisFourniseur comparerListDevis(DevisFourniseur devisFourniseur);
    public List<DevisFourniseur> getDevisfournisseurByIDAppel(int idAppelOffre);
}
