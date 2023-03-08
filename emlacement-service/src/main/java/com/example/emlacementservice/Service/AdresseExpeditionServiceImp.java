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

AdresseExpeditionRepository AdresseExpeditionRepository;
EmplacementRepository emplacementRepository;

    @Override
    public AdresseExpedition addAdresseExpedition(AdresseExpedition AdresseExpedition) {
        return AdresseExpeditionRepository.save(AdresseExpedition);
    }

    @Override
    public AdresseExpedition updateAdresseExpedition(AdresseExpedition AdresseExpedition) {
        return AdresseExpeditionRepository.save(AdresseExpedition);
    }

    @Override
    public void deleteAdresseExpedition(int id) {
        AdresseExpeditionRepository.deleteById(id);
    }

    @Override
    public AdresseExpedition getAdresseExpeditionById(int id) {
        return AdresseExpeditionRepository.findById(id).orElse(null);
    }

    @Override
    public List<AdresseExpedition> getAllAdresseExpeditions() {
        return AdresseExpeditionRepository.findAll();
    }

	@Override
	public AdresseExpedition affecterShippingAdresseToEmplac(AdresseExpedition ad, Integer ide) {
        Emplacement e =emplacementRepository.findById(ide).orElse(null);
        if(e.getAdresseExpedition() == null){
            e.setAdresseExpedition(ad);
        }


        return AdresseExpeditionRepository.save(ad);


	}
    @Override
    public List<AdresseExpedition> filterByPaysAndCite(String pays, String cite) {
        // TODO Auto-generated method stub
        return AdresseExpeditionRepository.findByPaysAndCite(pays, cite);
    }

}

