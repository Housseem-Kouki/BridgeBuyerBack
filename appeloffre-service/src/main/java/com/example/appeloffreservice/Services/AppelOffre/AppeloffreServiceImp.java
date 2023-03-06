package com.example.appeloffreservice.Services.AppelOffre;

import com.example.appeloffreservice.Entities.AppelOffre;
import com.example.appeloffreservice.Entities.DemandeAchat;
import com.example.appeloffreservice.Repository.AppelOffreRepository;
import com.example.appeloffreservice.Repository.DemandeAchatRepository;
import com.example.appeloffreservice.Repository.OffreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class AppeloffreServiceImp implements IAppeloffreService{
    AppelOffreRepository appelOffreRepository;
    DemandeAchatRepository demandeAchatRepository;
    OffreRepository offreRepository;

    @Override
    public AppelOffre addAndAssignAppelToDemande(AppelOffre appelOffre ,int idD ) {
        DemandeAchat demandeAchat=demandeAchatRepository.findById(idD).orElse(null);
        appelOffre.setPrixInitiale(demandeAchat.getBudget());
       appelOffre.setDemandeAchat(demandeAchat);

        return appelOffreRepository.save(appelOffre);
    }

    @Override
    public AppelOffre updateAppelOffre(AppelOffre appelOffre) {
        return appelOffreRepository.save(appelOffre);
    }

    @Override
    public void deleteAppelOffre(int id) {
        appelOffreRepository.deleteById(id);

    }

    @Override
    public AppelOffre getAppelOffrebyId(int id) {
        return appelOffreRepository.findById(id).orElse(null);

    }

    @Override
    public List<AppelOffre> getAllAppelOffre() {
        return appelOffreRepository.findAll();
    }

    @Override
    public List<AppelOffre> getAppelOffreByEtat(int etat) {
        List<AppelOffre> ap=appelOffreRepository.findAppelOffreByEtat(etat);
        AppelOffre A1=ap.get(0);
        if(A1.getEtat()==1){
            return ap;
        }else if (A1.getEtat()==2){
            return ap;
        }else {
            return ap;
        }

    }


    @Override
    public List<AppelOffre> retrieveAppelOffer(int etat) {
        return appelOffreRepository.findAppelOffreByEtat(etat);
    }

    @Override
    public List<AppelOffre> Search(String key) {
        if (key.equals("")){
            return (List<AppelOffre>) appelOffreRepository.findAll();
        }else
        {
            return appelOffreRepository.rech(key);
        }

    }

 /*   @Override
    public String getEtatt(int etat) {


    List<AppelOffre> la=appelOffreRepository.findAppelOffreByEtat(etat);
        for (AppelOffre a :la) {
            if (a.getEtat()== 0) {
                System.out.println("offre expire =" + etat);
            }
            if (a.getEtat() == 1) {
                System.out.println("offre en cours =" + etat);
            }
            if (a.getEtat() == 2) {
                System.out.println("offre fermee =" + etat);
            }
        }

        String res="l'appel d'offre est expiree\n"+
                    "l'appel d'offre est en cours\n"+
                    "l'appel d'offre est fermee\n";
        return res;

    }*/

}
