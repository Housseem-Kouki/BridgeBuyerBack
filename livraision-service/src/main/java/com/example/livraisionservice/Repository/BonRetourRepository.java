package com.example.livraisionservice.Repository;

import com.example.livraisionservice.Entities.BonRetour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BonRetourRepository extends JpaRepository<BonRetour, Integer> {
    @Query(value = "select br from BonRetour br where concat(br.idBonRetour,br.quantiteRefusee,br.commmentaire,br.archive) like %?1% ")
    List<BonRetour> recherche(String keyword);
}