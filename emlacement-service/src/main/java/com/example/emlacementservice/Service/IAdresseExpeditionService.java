package com.example.emlacementservice.Service;


import java.util.List;

import com.example.emlacementservice.Entities.AdresseExpedition;
import org.springframework.data.repository.query.Param;

public interface IAdresseExpeditionService {
    public AdresseExpedition addAdresseExpedition(AdresseExpedition AdresseExpedition);
    public AdresseExpedition updateAdresseExpedition(AdresseExpedition AdresseExpedition);
    public void deleteAdresseExpedition(int id);
    public AdresseExpedition getAdresseExpeditionById(int id);
    public List<AdresseExpedition> getAllAdresseExpeditions();
    void affecterShippingAdresseToEmplac(Integer idS , Integer idA);
    List<AdresseExpedition> filterByPaysAndCite(String pays , String cite);
}
