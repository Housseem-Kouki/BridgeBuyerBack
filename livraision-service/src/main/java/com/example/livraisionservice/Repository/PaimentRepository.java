package com.example.livraisionservice.Repository;



import com.example.livraisionservice.Entities.Paiment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaimentRepository extends JpaRepository<Paiment, Integer> {
}