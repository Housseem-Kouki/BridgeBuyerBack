package com.example.commandeservice.Repository;


import com.example.commandeservice.Entities.BonRetour;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BonRetourRepository extends JpaRepository<BonRetour, Integer> {
}