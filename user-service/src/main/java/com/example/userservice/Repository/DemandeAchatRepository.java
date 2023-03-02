package com.example.userservice.Repository;

import com.example.userservice.Entities.DemandeAchat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemandeAchatRepository extends JpaRepository<DemandeAchat, Integer> {
}