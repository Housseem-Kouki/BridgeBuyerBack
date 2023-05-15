package com.example.livraisionservice.Service;


import com.example.livraisionservice.Entities.Livraison;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ILivraisonService {
    public Livraison addAndAssignToCommande(int idCommande, Livraison l);
    public Livraison getLivraisonById(int id);
    public List<Livraison> getAllLivraison();
    public List<Livraison> getAllLivraisonUserContecter(Integer idUser);
    public List<Livraison> getLivraisonByFournisseur(Integer idUser);

    public void deleteLivraison(int id);

    public void restoreLivraison(int id);
    public Livraison updateLivraisonDate(int id, Date dateLivraison);
    public List<Livraison> sortLivraisonsByQuantity();
    //   public void modifierQuantiteLivraison(int idLivraison, Integer qteRecue, Integer qteRetour);
    public Livraison validerBL(int idLivraison) ;
    public List<Livraison> rechercheAvance(Integer quantiteDelivre, Date dateLivraison, String etat);
    // public void saveFile(int idLivraison, MultipartFile file);
    //public List<Livraison> SearchMultiple(String key) ;

    public void export(HttpServletResponse response , int idLivraison) throws IOException;

}
