package com.example.commandeservice.Repository;


import com.example.commandeservice.Entities.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FactureRepository extends JpaRepository<Facture, Integer> {
}