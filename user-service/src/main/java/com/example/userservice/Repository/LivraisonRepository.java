package com.example.userservice.Repository;


import com.example.userservice.Entities.Livraison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LivraisonRepository extends JpaRepository<Livraison, Integer> {
    @Query("SELECT idLivraison FROM Livraison ")
    public List<Integer> listdesIds();
}