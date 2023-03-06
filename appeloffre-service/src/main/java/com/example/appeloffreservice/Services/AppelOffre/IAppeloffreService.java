package com.example.appeloffreservice.Services.AppelOffre;

import com.example.appeloffreservice.Entities.AppelOffre;

import java.util.List;

public interface IAppeloffreService{
    public AppelOffre addAndAssignAppelToDemande(AppelOffre appelOffre , int idD);
    public AppelOffre updateAppelOffre(AppelOffre appelOffre);
    public void deleteAppelOffre(int id);
    public AppelOffre getAppelOffrebyId(int id);
    public List<AppelOffre> getAllAppelOffre();

    public List<AppelOffre> getAppelOffreByEtat(int etat);
    public List<AppelOffre> retrieveAppelOffer(int etat);


    public List<AppelOffre> Search(String key);


}
