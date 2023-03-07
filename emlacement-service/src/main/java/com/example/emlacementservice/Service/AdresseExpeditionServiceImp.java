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
	public void affecterShippingAdresseToEmplac(Integer idS, Integer idA) {
		Emplacement emplacement=emplacementRepository.findById(idA).orElse(null);
		AdresseExpedition adresseExpedition=AdresseExpeditionRepository.findById(idS).orElse(null);
		emplacement.setAdresseExpedition(adresseExpedition);
		emplacementRepository.save(emplacement);


	}
   /* @Scheduled(cron = "0 0 0 * * ?") // Exécuté tous les jours à minuit
    public void nettoyerAdressesInactives() {
        List<AdresseExpedition> adressesInactives = AdresseExpeditionRepository.findByActive(false);

        for (AdresseExpedition adresse : adressesInactives) {
            AdresseExpeditionRepository.delete(adresse);
        }
    }*/
}

