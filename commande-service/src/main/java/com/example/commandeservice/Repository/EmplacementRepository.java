package com.example.commandeservice.Repository;


import com.example.commandeservice.Entities.Emplacement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmplacementRepository extends JpaRepository<Emplacement, Integer> {
}