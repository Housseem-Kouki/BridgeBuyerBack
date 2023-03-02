package com.example.demandeachatservice.Repository;



import com.example.demandeachatservice.Entities.Devise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviseRepository extends JpaRepository<Devise, Integer> {
}