package com.example.commandeservice.Repository;



import com.example.commandeservice.Entities.Devise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviseRepository extends JpaRepository<Devise, Integer> {
}