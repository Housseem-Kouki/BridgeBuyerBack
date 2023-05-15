package com.example.userservice.Repository;

import com.example.userservice.Entities.DemandeAchat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DemandeAchatRepository extends JpaRepository<DemandeAchat, Integer> {

    @Query("SELECT iddemandeachat FROM DemandeAchat ")
    public List<Integer> listdesIds();
}