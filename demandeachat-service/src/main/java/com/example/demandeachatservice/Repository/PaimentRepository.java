package com.example.demandeachatservice.Repository;



import com.example.demandeachatservice.Entities.Paiment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaimentRepository extends JpaRepository<Paiment, Integer> {
}