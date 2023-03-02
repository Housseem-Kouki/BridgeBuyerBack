package com.example.livraisionservice.Service.BonReception;

import com.example.livraisionservice.Entities.BonReception;
import com.example.livraisionservice.Entities.BonRetour;
import com.example.livraisionservice.Repository.BonReceptionRepository;
import com.example.livraisionservice.Repository.BonRetourRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
@Service
@AllArgsConstructor
public class BonReceptionServiceImp implements IBonReceptionService{

    BonReceptionRepository bonReceptionRepository;
    BonRetourRepository bonRetourRepository;

    @Override
    public BonReception addBonReception(int idBonReception,int qteAccept,int qteRefus) {
        BonReception bonReception=bonReceptionRepository.findById(idBonReception).orElse(null);
        bonReception.setQuantiteAccepte(qteAccept);
        bonReception.setQuantiteRefuse(qteRefus);
        return bonReceptionRepository.save(bonReception);    }

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
    @Override
    public List<BonReception> SearchMultiple1(String key) {
        if (key.equals("")) {
            return (List<BonReception>) bonReceptionRepository.findAll();
        } else {
            return bonReceptionRepository.recherche(key);
        }
    }
    //affecter Bon de retour a un bon de recption
    public BonReception affecterBonRetourToBonRecept(int idBonReception, int idBonRetour) {
        BonReception bonReception = bonReceptionRepository.findById(idBonReception).orElse(null);
        BonRetour bonRetour = bonRetourRepository.findById(idBonRetour).orElse(null);
        bonReception.setBonRetour(bonRetour);
        return bonReceptionRepository.save(bonReception);
    }
}
