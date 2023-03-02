package com.example.commandeservice.Repository;


import com.example.commandeservice.Entities.Departement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartementRepository extends JpaRepository<Departement, Integer> {
}