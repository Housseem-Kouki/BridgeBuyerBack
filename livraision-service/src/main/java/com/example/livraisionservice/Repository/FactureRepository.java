package com.example.livraisionservice.Repository;


import com.example.livraisionservice.Entities.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FactureRepository extends JpaRepository<Facture, Integer> {
}