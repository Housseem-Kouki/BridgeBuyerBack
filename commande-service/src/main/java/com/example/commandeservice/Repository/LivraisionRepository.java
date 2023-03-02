package com.example.commandeservice.Repository;

import com.example.commandeservice.Entities.Livraison;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivraisionRepository extends JpaRepository<Livraison, Integer> {
}