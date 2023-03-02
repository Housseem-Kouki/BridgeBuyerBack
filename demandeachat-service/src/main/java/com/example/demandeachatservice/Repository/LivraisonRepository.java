package com.example.demandeachatservice.Repository;


import com.example.demandeachatservice.Entities.Livraison;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivraisonRepository extends JpaRepository<Livraison, Integer> {
}