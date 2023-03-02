package com.example.emlacementservice.Repository;

import com.example.emlacementservice.Entities.Emplacement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmplacementRepository extends JpaRepository<Emplacement, Integer> {
}