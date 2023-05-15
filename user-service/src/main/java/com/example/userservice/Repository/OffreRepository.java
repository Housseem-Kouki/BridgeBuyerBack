package com.example.userservice.Repository;


import com.example.userservice.Entities.Offre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OffreRepository extends JpaRepository<Offre, Integer> {
    @Query("SELECT idOffre FROM Offre ")
    public List<Integer> listdesIds();
}