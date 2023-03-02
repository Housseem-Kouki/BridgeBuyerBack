package com.example.emlacementservice.Repository;


import com.example.emlacementservice.Entities.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FactureRepository extends JpaRepository<Facture, Integer> {
}