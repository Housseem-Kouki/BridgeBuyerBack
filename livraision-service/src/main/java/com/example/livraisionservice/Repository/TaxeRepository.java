package com.example.livraisionservice.Repository;



import com.example.livraisionservice.Entities.Taxe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxeRepository extends JpaRepository<Taxe, Integer> {
}