package com.example.emlacementservice.Repository;

import com.example.emlacementservice.Entities.AdresseExpedition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdresseExpeditionRepository extends JpaRepository<AdresseExpedition, Integer> {
   // List<AdresseExpedition> findByActive(boolean b);
}