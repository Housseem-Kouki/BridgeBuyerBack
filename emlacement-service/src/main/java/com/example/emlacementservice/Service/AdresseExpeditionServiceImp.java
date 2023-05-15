package com.example.emlacementservice.Service;


import com.example.emlacementservice.Entities.AdresseExpedition;
import com.example.emlacementservice.Entities.Emplacement;
import com.example.emlacementservice.Repository.AdresseExpeditionRepository;
import com.example.emlacementservice.Repository.EmplacementRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdresseExpeditionServiceImp implements IAdresseExpeditionService {

    AdresseExpeditionRepository adresseExpeditionRepository;
    EmplacementRepository emplacementRepository;

    @Override
    public AdresseExpedition addAdresseExpedition(AdresseExpedition AdresseExpedition) {
        return adresseExpeditionRepository.save(AdresseExpedition);
    }

    @Override
    public AdresseExpedition updateAdresseExpedition(AdresseExpedition AdresseExpedition) {
        return adresseExpeditionRepository.save(AdresseExpedition);
    }

    @Override
    public void deleteAdresseExpedition(int id) {
        adresseExpeditionRepository.deleteById(id);
    }

    @Override
    public AdresseExpedition getAdresseExpeditionById(int id) {
        return adresseExpeditionRepository.findById(id).orElse(null);
    }

    @Override
    public List<AdresseExpedition> getAllAdresseExpeditions() {
        return adresseExpeditionRepository.findAll();
    }

    @Override
    public void affecterShippingAdresseToEmplac(Integer idS, Integer idA) {
        Emplacement emplacement=emplacementRepository.findById(idA).get();
        AdresseExpedition adresseExpedition=adresseExpeditionRepository.findById(idS).get();
        adresseExpedition.setEmplacement(emplacement);
        adresseExpeditionRepository.save(adresseExpedition);


    }

    @Override
    public List<AdresseExpedition> filterByPaysAndCite(String pays, String cite) {
        // TODO Auto-generated method stub
        return adresseExpeditionRepository.findByPaysAndCite(pays, cite);
    }
}
