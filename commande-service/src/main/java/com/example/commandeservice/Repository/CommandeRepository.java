package com.example.commandeservice.Repository;



import com.example.commandeservice.Entities.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommandeRepository extends JpaRepository<Commande, Integer> {

    @Query("select c from  Offre o, AppelOffre  aO,Commande c,DemandeAchat  d,User u,Paiment p " +
            "where   o.appelOffre.idAppelOffre=aO.idAppelOffre " +
            "and d.iddemandeachat=aO.demandeAchat.iddemandeachat and p.idPaiment=c.paiment.idPaiment and d.acheteur.idUser=?1")
    public List<Commande> getCommandeByUser(Integer idUser);



    @Query("select c from  Offre o, AppelOffre  aO,Commande c,DevisFourniseur d,User u , Paiment  p " +
            "where    o.appelOffre.idAppelOffre=aO.idAppelOffre " +
            "and  p.idPaiment=c.paiment.idPaiment and d.fourniseur.idUser=?1")
    public List<Commande> getCommandeFournisuer(Integer idUser);



    @Query("SELECT o.prixOffre FROM Offre o , Commande  c WHERE c.offre.idOffre=o.idOffre  and  c.paiment.idPaiment= ?1")
    Double findMontantById(int id);

}