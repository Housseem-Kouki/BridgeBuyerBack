package com.example.userservice.Repository;



import com.example.userservice.Entities.Taxe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaxeRepository extends JpaRepository<Taxe, Integer> {

    @Query("SELECT idTaxe FROM Taxe ")
    public List<Integer> listdesIds();
}