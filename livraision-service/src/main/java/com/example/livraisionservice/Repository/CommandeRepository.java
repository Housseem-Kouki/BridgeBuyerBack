package com.example.livraisionservice.Repository;


import com.example.livraisionservice.Entities.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeRepository extends JpaRepository<Commande, Integer> {
}