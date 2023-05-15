package com.example.userservice.Repository;


import com.example.userservice.Entities.ChargeFinanciere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChargeFinanciereRepository extends JpaRepository<ChargeFinanciere, Integer> {
    @Query("SELECT idChargeFinanciere FROM ChargeFinanciere ")
    public List<Integer> listdesIds();
}