package com.example.livraisionservice.Service.BonRetour;

import com.example.livraisionservice.Entities.BonRetour;
import com.example.livraisionservice.Entities.FactureAvoir;

import java.util.List;

public interface IBonRetourService {
    public BonRetour addBonRetour(BonRetour b , int idBonReception);
    public void deleteBonRetour(int id);
    public void restoreBonRetour(int id);
    public BonRetour getBonRetourById(int id);
    public List<BonRetour> getAllBonRetour();
    public List<BonRetour> SearchMultiple2(String key);
    public FactureAvoir affecterFactureAvoirToBonRetour(FactureAvoir f, int idBonRetour);
    //public BonRetour updateEtat(BonRetour b);
}
