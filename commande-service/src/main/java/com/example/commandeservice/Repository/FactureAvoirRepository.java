package com.example.commandeservice.Repository;



import com.example.commandeservice.Entities.FactureAvoir;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FactureAvoirRepository extends JpaRepository<FactureAvoir, Integer> {
}