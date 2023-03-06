package com.example.livraisionservice.Service.BonReception;

import com.example.livraisionservice.Entities.BonReception;

import java.util.List;

public interface IBonReceptionService {
    public BonReception addBonReception(int idBonReception, int qteAccept, int qteRefus);

    public BonReception updateEtat(BonReception br);
    public void deleteBonReception(int id);
    public BonReception getBonReceptionById(int id);
    public List<BonReception> getAllBonReception();
    public List<BonReception> getBonReceptByUser(Integer idUser);
    public List<BonReception> SearchMultiple1(String key);
    public BonReception affecterBonRetourToBonRecept(int idBonReception, int idBonRetour);
}
