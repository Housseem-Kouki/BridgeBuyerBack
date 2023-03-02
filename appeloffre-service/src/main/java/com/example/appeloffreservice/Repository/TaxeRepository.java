package com.example.appeloffreservice.Repository;



import com.example.appeloffreservice.Entities.Taxe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxeRepository extends JpaRepository<Taxe, Integer> {
}