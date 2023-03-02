package com.example.commandeservice.Repository;

import com.example.commandeservice.Entities.DemandeAchat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemandeAchatRepository extends JpaRepository<DemandeAchat, Integer> {
}