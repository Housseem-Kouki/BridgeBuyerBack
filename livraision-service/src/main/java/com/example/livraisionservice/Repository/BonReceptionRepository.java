package com.example.livraisionservice.Repository;


import com.example.livraisionservice.Entities.BonReception;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BonReceptionRepository extends JpaRepository<BonReception, Integer> {

    //Affichage
    @Query("select br from BonReception br,Livraison l, Offre o, AppelOffre  aO,Commande c,DemandeAchat  d,User u " +
            "where br.livraison.idLivraison=l.idLivraison "+
            "and l.commande.idCommande=c.idCommande "+
            "and o.appelOffre.idAppelOffre=aO.idAppelOffre " +
            "and d.iddemandeachat=aO.demandeAchat.iddemandeachat and d.acheteur.idUser=?1")
    public List<BonReception> getBonReceptByUser(Integer idUser);
    @Query(value = "select b from BonReception b where concat(b.idBonReception,b.dateReception,b.quantiteRecevoir,b.etat,b.archive) like %?1% ")
    List<BonReception> recherche(String keyword);
}