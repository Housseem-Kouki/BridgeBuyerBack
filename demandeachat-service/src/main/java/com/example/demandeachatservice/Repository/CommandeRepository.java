package com.example.demandeachatservice.Repository;



import com.example.demandeachatservice.Entities.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeRepository extends JpaRepository<Commande, Integer> {
}