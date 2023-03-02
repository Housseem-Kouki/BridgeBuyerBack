package com.example.livraisionservice.Repository;


import com.example.livraisionservice.Entities.Offre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OffreRepository extends JpaRepository<Offre, Integer> {
}