package com.example.commandeservice.Repository;


import com.example.commandeservice.Entities.AdresseDepartement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdresseDepartementRepository extends JpaRepository<AdresseDepartement, Integer> {
}