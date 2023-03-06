package com.example.appeloffreservice.Services.AppelOffre;

import com.example.appeloffreservice.Entities.AppelOffre;
import com.example.appeloffreservice.Entities.DevisFourniseur;
import com.example.appeloffreservice.Entities.User;
import com.example.appeloffreservice.Repository.AppelOffreRepository;
import com.example.appeloffreservice.Repository.DemandeAchatRepository;
import com.example.appeloffreservice.Repository.DevisFourniseurRepository;
import com.example.appeloffreservice.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class DevicFournisseurServiceImp implements IDevisFournisseurService
{
    DevisFourniseurRepository devisFourniseurRepository;
    AppelOffreRepository appelOffreRepository;
    UserRepository userRepository;
    private final DemandeAchatRepository demandeAchatRepository;

    @Override
    public DevisFourniseur addDevisFourniseur(DevisFourniseur devisFourniseur) {
        return devisFourniseurRepository.save(devisFourniseur);
    }

    @Override
    public DevisFourniseur updateDevisFourniseur(DevisFourniseur devisFourniseur) {
        return devisFourniseurRepository.save(devisFourniseur);
    }

    @Override
    public void deleteDevisFourniseur(int id) {
        devisFourniseurRepository.deleteById(id);
    }

    @Override
    public DevisFourniseur getDevisFourniseurbyId(int id) {
        return devisFourniseurRepository.findById(id).orElse(null) ;
    }


    @Override
    public List<DevisFourniseur> getAllDevisFourniseur() {
        return devisFourniseurRepository.findAll();
    }



    //ykhtar bel min prix w delais + disponible + etat expire
    @Override
    public DevisFourniseur comparerDevis(int id1 , int id2) {
        List<DevisFourniseur> df=devisFourniseurRepository.findByIds(id1,id2);
        DevisFourniseur df1=df.get(0);
       DevisFourniseur df2=df.get(1);
            if ((df1.getPtotal()<df2.getPtotal()) && (df1.getDelaiLivraison()<df2.getDelaiLivraison()) && (df1.isDisopnible() ) && (df1.getEtat()==1)){
                return df1;
            }else {
                return null;
            }


    }

    @Override
    public double calculremise(int id1) {
        List<DevisFourniseur> devis=devisFourniseurRepository.findDevisFourniseursByIdDevisFourniseur(id1);

        DevisFourniseur devisFourniseur=devisFourniseurRepository.findById(id1).orElse(null);
        DevisFourniseur devis1=devis.get(0);
        double Ptotal = devis1.getPrixInitiale() - (devis1.getPourcentageRemise() * devis1.getPrixInitiale() / 100);
        devisFourniseur.setPtotal(Ptotal);
        devisFourniseurRepository.save(devisFourniseur);
        return Ptotal;
    }

    @Override
    public void assignAppeloffreAndUserToDevis( int idAppeloffre ,int idDevis, int idUser) {
        AppelOffre appelOffre =appelOffreRepository.findById(idAppeloffre).orElse(null);
        DevisFourniseur devisFourniseur= devisFourniseurRepository.findById(idDevis).orElse(null);
        User user=userRepository.findById(idUser).orElse(null);
        devisFourniseur.setFourniseur(user);
        devisFourniseur.setAppelOffre(appelOffre);
        devisFourniseur.setPrixInitiale(appelOffre.getPrixInitiale());
        devisFourniseurRepository.save(devisFourniseur);
    }

    //@Override
    //@Scheduled(cron = "0 0 12 28 1/1  *")
   /* @Scheduled(cron = "*///60 * * * * *")
   /* public void deleteExpiredOffer() {
        Date d = new Date();
        List<DevisFourniseur> offers = devisFourniseurRepository.findAll();
        for(DevisFourniseur df : offers){
            if(df.getPeriodeValidite().after(d)){
                devisFourniseurRepository.delete(df);
                System.out.println("Devis expiré");
            }
        }
    }*/
}
