package com.example.emlacementservice.Repository;


import com.example.emlacementservice.Entities.Departement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartementRepository extends JpaRepository<Departement, Integer> {
}