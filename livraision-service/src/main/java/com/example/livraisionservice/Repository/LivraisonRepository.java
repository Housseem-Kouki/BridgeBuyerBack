package com.example.livraisionservice.Repository;


import com.example.livraisionservice.Entities.Livraison;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivraisonRepository extends JpaRepository<Livraison, Integer> {
}