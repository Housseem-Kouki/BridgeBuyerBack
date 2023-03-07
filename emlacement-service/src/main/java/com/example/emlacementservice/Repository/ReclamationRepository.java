package com.example.emlacementservice.Repository;

import com.example.emlacementservice.Entities.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReclamationRepository extends JpaRepository<Reclamation, Long> {
  /*  @Query(value="select mots from dictionnaire", nativeQuery=true)
    List<String> Dictionnaire();
*/
}