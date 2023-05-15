package com.example.appeloffreservice.Services.AppelOffre;

import com.example.appeloffreservice.Entities.AppelOffre;
import com.example.appeloffreservice.Entities.DevisFourniseur;
import com.example.appeloffreservice.Entities.Offre;
import com.example.appeloffreservice.Repository.AppelOffreRepository;
import com.example.appeloffreservice.Repository.DevisFourniseurRepository;
import com.example.appeloffreservice.Repository.OffreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OffreServiceImp implements IOffreService{
    OffreRepository offreRepository;
    AppelOffreRepository appelOffreRepository;
    private final DevisFourniseurRepository devisFourniseurRepository;

    @Override
    public Offre addAndAssignOffreToAppel(Offre offre , int idA) {

        AppelOffre appelOffre=appelOffreRepository.findById(idA).orElse(null);
        offre.setPrixOffre(appelOffre.getPrixInitiale());
        appelOffre.setOffre(offre);
        appelOffre.setEtat(1);// appeloffre en cours

        return offreRepository.save(offre);
    }
    @Override
    public Offre addoffre( Offre offre, int idDevisFourniseur) {
        // Offre offre= new Offre();
        DevisFourniseur devisFourniseur= devisFourniseurRepository.findById(idDevisFourniseur).orElse(null);

        AppelOffre appelOffre=appelOffreRepository.findById(devisFourniseur.getAppelOffre().getIdAppelOffre()).orElse(null);
        offre.setPrixOffre(devisFourniseur.getPrixInitiale());
        appelOffre.setOffre(offre);
        appelOffre.setEtat(1);// appeloffre acceptee
        devisFourniseur.setEtat(1); // devis acceeptee

        return offreRepository.save(offre);
    }


    @Override
    public Offre updateOffre(Offre offre) {
        return offreRepository.save(offre);
    }

    @Override
    public void deleteOffre(int id) {
        offreRepository.deleteById(id);
    }

    @Override
    public Offre getOffrebyId(int id) {
        return offreRepository.findById(id).orElse(null);
    }

    @Override
    public List<Offre> getAllOffre() {
        return offreRepository.findAll();
    }

    @Override
    public List<Offre> getOffreByEtat(int etat) {
        return offreRepository.findOffreByEtat(etat);
    }
    @Override
    public void assignofferToAppelOffre(int idAppeloffre, int idOffer) {
        AppelOffre appelOffre =appelOffreRepository.findById(idAppeloffre).orElse(null);
        Offre offre= offreRepository.findById(idOffer).orElse(null);
        appelOffre.setOffre(offre);
        appelOffre.setEtat(1);// appeloffre en cours
        appelOffreRepository.save(appelOffre);
    }
    @Override
    public List<Offre> findByCriteria(String commentaire, double prixOffre) {
        return offreRepository.findAll()
                .stream()
                .filter(offre -> commentaire == null || offre.getCommentaire().contains(commentaire))
                .filter(offre -> prixOffre ==0 || offre.getPrixOffre()==prixOffre)
                .collect(Collectors.toList());
    }

}
