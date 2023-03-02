package com.example.commandeservice.Repository;



import com.example.commandeservice.Entities.Paiment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaimentRepository extends JpaRepository<Paiment, Integer> {
}