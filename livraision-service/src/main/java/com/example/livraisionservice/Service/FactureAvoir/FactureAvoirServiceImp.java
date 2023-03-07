package com.example.livraisionservice.Service.FactureAvoir;

import com.example.livraisionservice.Entities.FactureAvoir;
import com.example.livraisionservice.Repository.FactureAvoirRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FactureAvoirServiceImp implements IFactureAvoirService{
    FactureAvoirRepository factureAvoirRepository;
    @Override
    public FactureAvoir addFactureAvoir(FactureAvoir f) {
        return factureAvoirRepository.save(f);
    }



    @Override
    public void deleteFactureAvoir(int id) {
        factureAvoirRepository.deleteById(id);

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
            return (List<FactureAvoir>) factureAvoirRepository.findAll();
        } else {
            return factureAvoirRepository.recherche(key);
        }
    }
}
