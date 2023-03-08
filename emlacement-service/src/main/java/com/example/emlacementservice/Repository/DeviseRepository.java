package com.example.emlacementservice.Repository;


import com.example.emlacementservice.Entities.Devise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviseRepository extends JpaRepository<Devise, Integer> {
    Devise findByNomDevise(String nom);
}