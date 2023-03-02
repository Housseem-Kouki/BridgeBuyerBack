package com.example.demandeachatservice.Repository;


import com.example.demandeachatservice.Entities.Emplacement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmplacementRepository extends JpaRepository<Emplacement, Integer> {
}