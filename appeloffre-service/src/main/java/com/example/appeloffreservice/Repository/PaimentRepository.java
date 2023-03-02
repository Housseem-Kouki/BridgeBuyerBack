package com.example.appeloffreservice.Repository;



import com.example.appeloffreservice.Entities.Paiment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaimentRepository extends JpaRepository<Paiment, Integer> {
}