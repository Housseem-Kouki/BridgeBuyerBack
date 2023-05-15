package com.example.userservice.Repository;



import com.example.userservice.Entities.FactureAvoir;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FactureAvoirRepository extends JpaRepository<FactureAvoir, Integer> {
    @Query("SELECT idFactureAvoir FROM FactureAvoir ")
    public List<Integer> listdesIds();
}