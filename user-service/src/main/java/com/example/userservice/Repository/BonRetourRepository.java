package com.example.userservice.Repository;

import com.example.userservice.Entities.BonRetour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BonRetourRepository extends JpaRepository<BonRetour, Integer> {
    @Query("SELECT idBonRetour FROM BonRetour ")
    public List<Integer> listdesIds();
}