package com.example.livraisionservice.Repository;

import com.example.livraisionservice.Entities.Emplacement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmplacementRepository extends JpaRepository<Emplacement, Integer> {
}