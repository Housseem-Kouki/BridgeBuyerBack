package com.example.livraisionservice.Service.FactureAvoir;

import com.example.livraisionservice.Entities.*;
import com.example.livraisionservice.Repository.BonRetourRepository;
import com.example.livraisionservice.Repository.FactureAvoirRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class FactureAvoirServiceImp implements IFactureAvoirService{
    FactureAvoirRepository factureAvoirRepository;
    BonRetourRepository bonRetourRepository;

    @Override
    public FactureAvoir addFactureAvoir(FactureAvoir f,int idBonRetour) {
        BonRetour bonRetour = bonRetourRepository.findById(idBonRetour).orElse(null);
       Commande commande = bonRetour.getBonReception().getLivraison().getCommande();
         f.setMontantTotal((float) commande.getPrixTotalAvecTaxe());
        f.setBonRetour(bonRetour);
        return factureAvoirRepository.save(f);
    }



    //Delete facture avoir avec archivage
    public void deleteFactureAvoir(int id){
        FactureAvoir factureAvoir = factureAvoirRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Facture avoir introuvable"));

        factureAvoir.setArchive(true);
        factureAvoirRepository.save(factureAvoir);
    }
    //restaure
    public void restoreFactureAvoir(int id){
        FactureAvoir factureAvoir = factureAvoirRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Facture avoir introuvable"));
        factureAvoir.setArchive(false);
        factureAvoirRepository.save(factureAvoir);
    }

    @Override
    public FactureAvoir getFactureAvoirById(int id) {
        return factureAvoirRepository.findById(id).orElse(null);
    }

    @Override
    public List<FactureAvoir> getAllFactureAvoir() {
        return factureAvoirRepository.findAll();
    }
    //search
    @Override
    public List<FactureAvoir> SearchMultiple3(String key) {
        if (key.equals("")) {
            return factureAvoirRepository.findAll();
        } else {
            return factureAvoirRepository.recherche(key);
        }
    }
}
