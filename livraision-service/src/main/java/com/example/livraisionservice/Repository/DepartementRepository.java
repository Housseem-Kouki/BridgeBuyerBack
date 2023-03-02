package com.example.livraisionservice.Repository;


import com.example.livraisionservice.Entities.Departement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartementRepository extends JpaRepository<Departement, Integer> {
}