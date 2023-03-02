package com.example.appeloffreservice.Repository;

import com.example.appeloffreservice.Entities.Emplacement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmplacementRepository extends JpaRepository<Emplacement, Integer> {
}