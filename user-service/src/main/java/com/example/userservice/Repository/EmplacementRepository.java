package com.example.userservice.Repository;

import com.example.userservice.Entities.Emplacement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmplacementRepository extends JpaRepository<Emplacement, Integer> {
    @Query("SELECT idEmplacement FROM Emplacement ")
    public List<Integer> listdesIds();
}