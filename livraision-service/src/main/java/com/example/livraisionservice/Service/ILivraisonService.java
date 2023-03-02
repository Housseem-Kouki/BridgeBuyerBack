package com.example.livraisionservice.Service;


import com.example.livraisionservice.Entities.Livraison;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ILivraisonService {
    public Livraison addLivraison(Livraison l);
    public Livraison addAndAssignToCommande(int idCommande, Livraison l);
    public Livraison getLivraisonById(int id);
    public List<Livraison> getAllLivraison();
    public List<Livraison> getAllLivraisonUserContecter(Integer idUser);
    public List<Livraison> getLivraisonByFournisseur(Integer idUser);
    public Livraison affecterBonReceptToBonLivr(int idBonReception, int idLivraision);
    public List<Livraison> SearchMultiple(String key);
    public void deleteLivraison(int id);
    // public List<Livraison> getLivraisonByCommande(int idCommande);
    public Livraison updateLivraisonDate(int id, Date dateLivraison);
    public List<Livraison> sortLivraisonsByQuantity();
    public void modifierQuantiteLivraison(int idLivraison, Integer qteRecue, Integer qteRetour);
    public Livraison validerBL(int idLivraison) ;
}
