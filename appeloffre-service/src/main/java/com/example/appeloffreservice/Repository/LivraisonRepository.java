package com.example.appeloffreservice.Repository;


import com.example.appeloffreservice.Entities.Livraison;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivraisonRepository extends JpaRepository<Livraison, Integer> {
}