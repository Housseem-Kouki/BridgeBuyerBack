package com.example.appeloffreservice.Repository;


import com.example.appeloffreservice.Entities.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FactureRepository extends JpaRepository<Facture, Integer> {
}