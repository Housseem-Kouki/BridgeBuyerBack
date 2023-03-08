package com.example.emlacementservice.Repository;

import com.example.emlacementservice.Entities.AdresseDepartement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdresseDepartementRepository extends JpaRepository<AdresseDepartement, Integer> {
}