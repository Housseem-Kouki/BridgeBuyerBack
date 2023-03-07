package com.example.emlacementservice.Repository;

import com.example.emlacementservice.Entities.Emplacement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmplacementRepository extends JpaRepository<Emplacement, Integer> {

    //List<Emplacement> getEmplacementOlder();

   // List<Emplacement> findByUserIsNull();
}