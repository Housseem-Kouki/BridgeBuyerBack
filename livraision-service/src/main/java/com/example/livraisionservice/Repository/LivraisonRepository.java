package com.example.livraisionservice.Repository;


import com.example.livraisionservice.Entities.Livraison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LivraisonRepository extends JpaRepository<Livraison, Integer> {
    //Affichage
    @Query("select l from Livraison l, Offre o, AppelOffre  aO,Commande c,DemandeAchat  d,User u " +
            "where l.commande.idCommande = c.idCommande " +
            "and o.appelOffre.idAppelOffre=aO.idAppelOffre " +
            "and aO.demandeAchat.iddemandeachat=d.iddemandeachat and d.acheteur.idUser=?1 ")
    public List<Livraison> getLivraisonByCommande(Integer idUser);

    @Query("select l from Livraison l, Offre o, AppelOffre  aO,Commande c,DevisFourniseur  df,User u " +
            "where l.commande.idCommande = c.idCommande " +
            "and o.appelOffre.idAppelOffre=aO.idAppelOffre " +
            "and aO.idAppelOffre=df.appelOffre.idAppelOffre and df.fourniseur.idUser=?1")
    public List<Livraison> getLivraisonByFournisseur(Integer idUser);

    //Recherche
    @Query(value = "select l from Livraison l where concat(l.idLivraison,l.quantiteDelivre,l.dateLivraison,l.archive) like %?1% ")
    List<Livraison> recherche(String keyword);


    //Tri
    /*
   @Query(value = "select * from livraison order by quantite_delivre ASC",nativeQuery = true)
    public void sortLivraisonByQuantity(List<Livraison> livraisons);*/
    List<Livraison> findAllByOrderByQuantiteDelivreAsc();

}