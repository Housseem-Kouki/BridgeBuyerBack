package com.example.userservice.Repository;


import com.example.userservice.Entities.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FactureRepository extends JpaRepository<Facture, Integer> {
    @Query("SELECT idFacture FROM Facture ")
    public List<Integer> listdesIds();
}