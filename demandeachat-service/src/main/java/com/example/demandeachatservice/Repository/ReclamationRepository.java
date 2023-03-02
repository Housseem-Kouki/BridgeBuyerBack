package com.example.demandeachatservice.Repository;

import com.example.demandeachatservice.Entities.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReclamationRepository extends JpaRepository<Reclamation, Long> {
}