package com.example.demandeachatservice.Repository;


import com.example.demandeachatservice.Entities.Departement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartementRepository extends JpaRepository<Departement, Integer> {
}