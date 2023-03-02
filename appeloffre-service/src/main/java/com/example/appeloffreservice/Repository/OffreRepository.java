package com.example.appeloffreservice.Repository;


import com.example.appeloffreservice.Entities.Offre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OffreRepository extends JpaRepository<Offre, Integer> {
}