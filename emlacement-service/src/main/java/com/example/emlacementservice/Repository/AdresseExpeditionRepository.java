package com.example.emlacementservice.Repository;

import com.example.emlacementservice.Entities.AdresseExpedition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdresseExpeditionRepository extends JpaRepository<AdresseExpedition, Integer> {
    @Query("SELECT AE FROM AdresseExpedition AE WHERE AE.pays=:pays And AE.cite=:cite")
    List<AdresseExpedition> findByPaysAndCite(@Param("pays") String pays , @Param("cite") String cite);
}