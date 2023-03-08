package com.example.livraisionservice.Service.BonReception;

import com.example.livraisionservice.Entities.BonReception;

import java.util.Date;
import java.util.List;

public interface IBonReceptionService {
    public BonReception addBonReception(int idLivraison ,BonReception b);

    public BonReception updateEtat(BonReception br);
    public void deleteBonReception(int id);
    public void restoreBonReception(int id);
    public BonReception getBonReceptionById(int id);
    public List<BonReception> getAllBonReception();
    public List<BonReception> getBonReceptByUser(Integer idUser);
    // public List<BonReception> SearchMultiple1(String key);
    public List<BonReception> rechercheAvance1(Date dateReception, Integer quantiteRecevoir, Integer quantiteAccepte, String etat);
}
