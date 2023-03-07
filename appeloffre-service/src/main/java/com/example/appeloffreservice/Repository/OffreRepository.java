package com.example.appeloffreservice.Repository;


import com.example.appeloffreservice.Entities.Offre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OffreRepository extends JpaRepository<Offre, Integer> {
    List<Offre> findOffreByEtat(int etat);
}