package com.example.emlacementservice.Service;

import com.example.emlacementservice.Entities.AdresseExpedition;

import java.util.List;

public interface IAdresseExpeditionService {
    public AdresseExpedition addAdresseExpedition(AdresseExpedition AdresseExpedition);
    public AdresseExpedition updateAdresseExpedition(AdresseExpedition AdresseExpedition);
    public void deleteAdresseExpedition(int id);
    public AdresseExpedition getAdresseExpeditionById(int id);
    public List<AdresseExpedition> getAllAdresseExpeditions();

    void affecterShippingAdresseToEmplac(Integer idS , Integer idA);
}
