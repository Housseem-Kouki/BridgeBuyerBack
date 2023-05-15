package com.example.livraisionservice.Service.BonReception;

import com.example.livraisionservice.Entities.BonReception;
import com.example.livraisionservice.Entities.BonRetour;
import com.example.livraisionservice.Entities.Livraison;
import com.example.livraisionservice.Repository.BonReceptionRepository;
import com.example.livraisionservice.Repository.BonRetourRepository;
import com.example.livraisionservice.Repository.LivraisonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BonReceptionServiceImp implements IBonReceptionService{

    BonReceptionRepository bonReceptionRepository;
    BonRetourRepository bonRetourRepository;
    LivraisonRepository livraisonRepository;

    @Override
    public BonReception addBonReception(int idLivraison,BonReception b) {
        Livraison livraison = livraisonRepository.findById(idLivraison).orElse(null);

        b.setLivraison(livraison);


        return bonReceptionRepository.save(b);    }

    @Override
    public BonReception updateEtat(BonReception br) {
        if (br.getQuantiteAccepte()==br.getQuantiteRecevoir())
        {
            br.setEtat("Reçu");
        }
        else
        if (br.getQuantiteRefuse()>=0){
            br.setEtat("Partiellement Reçu");
        }

        return bonReceptionRepository.save(br);
    }

    @Override
    public void deleteBonReception(int id) {
        BonReception bonReception = bonReceptionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Bon reception introuvable"));

        bonReception.setArchive(true);
        bonReceptionRepository.save(bonReception);
    }
    //restaure
    @Override
    public void restoreBonReception(int id){
        BonReception bonReception = bonReceptionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Bon Reception introuvable"));
        bonReception.setArchive(false);
        bonReceptionRepository.save(bonReception);
    }

    @Override
    public BonReception getBonReceptionById(int id) {
        return bonReceptionRepository.findById(id).orElse(null);
    }

    @Override
    public List<BonReception> getAllBonReception() {
        return bonReceptionRepository.findAll();
    }
    //Acheteur
    @Override
    public List<BonReception> getBonReceptByUser(Integer idUser) {
        return bonReceptionRepository.getBonReceptByUser(idUser);
    }
    //search
   /* @Override
    public List<BonReception> SearchMultiple1(String key) {
        if (key.equals("")) {
            return (List<BonReception>) bonReceptionRepository.findAll();
        } else {
            return bonReceptionRepository.recherche(key);
        }
    }*/

    @Override
    public List<BonReception> rechercheAvance1(Date dateReception, Integer quantiteRecevoir, Integer quantiteAccepte, String etat)
    {
        return bonReceptionRepository.findAll()
                .stream()
                .filter(bonReception -> dateReception == null || bonReception.getDateReception()==dateReception)
                .filter(bonReception -> quantiteRecevoir == null || bonReception.getQuantiteRecevoir()==quantiteRecevoir)
                .filter(bonReception -> quantiteAccepte == null || bonReception.getQuantiteAccepte()==quantiteAccepte)
                .filter(bonReception -> etat == null || bonReception.getEtat().contains(etat))
                .collect(Collectors.toList());
    }
}
