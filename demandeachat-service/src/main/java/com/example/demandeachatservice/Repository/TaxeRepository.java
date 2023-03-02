package com.example.demandeachatservice.Repository;



import com.example.demandeachatservice.Entities.Taxe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxeRepository extends JpaRepository<Taxe, Integer> {
}