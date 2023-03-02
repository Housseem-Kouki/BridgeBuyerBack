package com.example.userservice.Repository;



import com.example.userservice.Entities.Taxe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxeRepository extends JpaRepository<Taxe, Integer> {
}