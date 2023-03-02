package com.example.demandeachatservice.Repository;

import com.example.demandeachatservice.Entities.Offre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OffreRepository extends JpaRepository<Offre, Integer> {
}