package com.example.emlacementservice.Service;

import com.example.emlacementservice.Entities.AdresseExpedition;
import com.example.emlacementservice.Entities.Emplacement;

import java.util.List;

public interface IAdresseExpeditionService {
    public AdresseExpedition addAdresseExpedition(AdresseExpedition AdresseExpedition);
    public AdresseExpedition updateAdresseExpedition(AdresseExpedition AdresseExpedition);
    public void deleteAdresseExpedition(int id);
    public AdresseExpedition getAdresseExpeditionById(int id);
    public List<AdresseExpedition> getAllAdresseExpeditions();


    AdresseExpedition affecterShippingAdresseToEmplac(AdresseExpedition ad, Integer ide);

    List<AdresseExpedition> filterByPaysAndCite(String pays, String cite);
}
