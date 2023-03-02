package com.example.userservice.Repository;



import com.example.userservice.Entities.Paiment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaimentRepository extends JpaRepository<Paiment, Integer> {
}