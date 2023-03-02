package com.example.emlacementservice.Repository;



import com.example.emlacementservice.Entities.Taxe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxeRepository extends JpaRepository<Taxe, Integer> {
}