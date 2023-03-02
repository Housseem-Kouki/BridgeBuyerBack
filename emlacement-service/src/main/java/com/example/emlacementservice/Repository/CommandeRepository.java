package com.example.emlacementservice.Repository;


import com.example.emlacementservice.Entities.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeRepository extends JpaRepository<Commande, Integer> {
}