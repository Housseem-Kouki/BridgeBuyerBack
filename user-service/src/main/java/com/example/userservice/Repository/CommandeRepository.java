package com.example.userservice.Repository;


import com.example.userservice.Entities.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommandeRepository extends JpaRepository<Commande, Integer> {
    @Query("SELECT idCommande FROM Commande ")
    public List<Integer> listdesIds();
}