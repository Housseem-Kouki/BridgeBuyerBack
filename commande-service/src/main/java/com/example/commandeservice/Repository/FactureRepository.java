package com.example.commandeservice.Repository;


import com.example.commandeservice.Entities.ChargeFinanciere;
import com.example.commandeservice.Entities.Facture;
import com.example.commandeservice.Entities.Taxe;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FactureRepository extends JpaRepository<Facture, Integer> {

    @Query("SELECT f FROM Facture f JOIN FETCH f.commande c WHERE f.idFacture = :idFacture")
    Facture findByIdWithCommande(@Param("idFacture") int idFacture);

    @Query("SELECT t FROM Taxe t JOIN FETCH t.factures f WHERE f.idFacture = :idFacture")
    List<Taxe> findTaxesByFactureId(@Param("idFacture") int idFacture);

    @Query("SELECT c FROM ChargeFinanciere c JOIN FETCH c.factures f WHERE f.idFacture = :idFacture")
    List<ChargeFinanciere> findChargesByFactureId(@Param("idFacture") int idFacture);
}