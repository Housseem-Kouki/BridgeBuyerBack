package com.example.emlacementservice.Repository;


import com.example.emlacementservice.Entities.Livraison;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivraisonRepository extends JpaRepository<Livraison, Integer> {
}