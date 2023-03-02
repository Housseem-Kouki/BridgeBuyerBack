package com.example.appeloffreservice.Repository;


import com.example.appeloffreservice.Entities.Departement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartementRepository extends JpaRepository<Departement, Integer> {
}