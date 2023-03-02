package com.example.appeloffreservice.Repository;


import com.example.appeloffreservice.Entities.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeRepository extends JpaRepository<Commande, Integer> {
}