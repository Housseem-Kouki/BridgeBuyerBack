package com.example.demandeachatservice.Repository;


import com.example.demandeachatservice.Entities.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FactureRepository extends JpaRepository<Facture, Integer> {
}