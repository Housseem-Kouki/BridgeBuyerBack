package com.example.userservice.Repository;


import com.example.userservice.Entities.Devise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviseRepository extends JpaRepository<Devise, Integer> {
}