package com.example.emlacementservice.Repository;

import com.example.emlacementservice.Entities.AdresseDepartement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdresseDepartementRepository extends JpaRepository<AdresseDepartement, Integer> {
    @Query("SELECT Ad FROM AdresseDepartement Ad WHERE Ad.emplacementDepartement=:dep And Ad.codePostal=:c")
    List<AdresseDepartement> findByDepAndCode(@Param("dep") String dep , @Param("c") String c);
}