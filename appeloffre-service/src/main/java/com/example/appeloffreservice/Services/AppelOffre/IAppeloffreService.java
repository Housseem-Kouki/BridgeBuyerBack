package com.example.appeloffreservice.Services.AppelOffre;

import com.example.appeloffreservice.Entities.AppelOffre;
import com.example.appeloffreservice.Entities.Article;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface IAppeloffreService{
    public AppelOffre addAndAssignAppelToDemande(AppelOffre appelOffre , int iddemandeachat);
    public AppelOffre updateAppelOffre(AppelOffre appelOffre);
    public void deleteAppelOffre(int id);
    public AppelOffre getAppelOffrebyId(int id);
    public List<AppelOffre> getAllAppelOffre();

    public List<AppelOffre> getAppelOffreByEtat(int etat);
    public List<AppelOffre> retrieveAppelOffer(int etat);


    public List<AppelOffre> Search(String key);
    public List<AppelOffre> findByCriteria(String reference, String commentaire, double prixInitiale);
    public void export(HttpServletResponse response , int idAppel) throws IOException ;


    //List<AppelOffre> SearchAppeloffre(String nomarticle);
}
