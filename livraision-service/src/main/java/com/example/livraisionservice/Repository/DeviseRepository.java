package com.example.livraisionservice.Repository;


import com.example.livraisionservice.Entities.Devise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviseRepository extends JpaRepository<Devise, Integer> {
}