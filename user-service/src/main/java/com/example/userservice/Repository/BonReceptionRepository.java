package com.example.userservice.Repository;


import com.example.userservice.Entities.BonReception;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BonReceptionRepository extends JpaRepository<BonReception, Integer> {
    @Query("SELECT idBonReception FROM BonReception ")
    public List<Integer> listdesIds();
}