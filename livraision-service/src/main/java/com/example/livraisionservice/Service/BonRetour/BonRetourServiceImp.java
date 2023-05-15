package com.example.livraisionservice.Service.BonRetour;

import com.example.livraisionservice.Entities.BonReception;
import com.example.livraisionservice.Entities.BonRetour;
import com.example.livraisionservice.Entities.FactureAvoir;
import com.example.livraisionservice.Repository.BonReceptionRepository;
import com.example.livraisionservice.Repository.BonRetourRepository;
import com.example.livraisionservice.Repository.FactureAvoirRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@AllArgsConstructor
public class BonRetourServiceImp implements IBonRetourService{
    BonRetourRepository bonRetourRepository;
    FactureAvoirRepository factureAvoirRepository;
    BonReceptionRepository bonReceptionRepository;

    @Override
    public BonRetour addBonRetour(BonRetour b , int idBonReception  ){
        BonReception br =bonReceptionRepository.findById(idBonReception).orElse(null) ;
        b.setBonReception(br) ;
        return bonRetourRepository.save(b);
    }
    @Override
    public FactureAvoir affecterFactureAvoirToBonRetour(FactureAvoir f, int idBonRetour){
        BonRetour bonRetour = bonRetourRepository.findById(idBonRetour).orElse(null);
        bonRetour.setFactureAvoir(f);
        f.setIdFactureAvoir(bonRetour.getIdBonRetour());
        return factureAvoirRepository.save(f);
    }
    @Override
    public BonRetour getBonRetourById(int id) {
        return bonRetourRepository.findById(id).orElse(null);
    }

    @Override
    public List<BonRetour> getAllBonRetour() {
        return bonRetourRepository.findAll();
    }

    @Override
    public  void deleteBonRetour(int id) {
        BonRetour bonRetour = bonRetourRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Bon retour introuvable"));

        bonRetour.setArchive(true);
        bonRetourRepository.save(bonRetour);
    }

    //restaure
    @Override
    public void restoreBonRetour(int id){
        BonRetour bonRetour = bonRetourRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Bon retour introuvable"));
        bonRetour.setArchive(false);
        bonRetourRepository.save(bonRetour);
    }
    //search
    @Override
    public List<BonRetour> SearchMultiple2(String key) {
        if (key.equals("")) {
            return (List<BonRetour>) bonRetourRepository.findAll();
        } else {
            return bonRetourRepository.recherche(key);
        }
    }


}
