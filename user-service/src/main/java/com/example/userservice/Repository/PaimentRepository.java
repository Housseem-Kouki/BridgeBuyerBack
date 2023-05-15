package com.example.userservice.Repository;



import com.example.userservice.Entities.Paiment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaimentRepository extends JpaRepository<Paiment, Integer> {
    @Query("SELECT idOffre FROM Offre ")
    public List<Integer> listdesIds();
}