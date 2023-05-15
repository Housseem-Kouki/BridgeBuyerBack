package com.example.emlacementservice.Repository;


import com.example.emlacementservice.Entities.Emplacement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmplacementRepository extends JpaRepository<Emplacement, Integer> {
    List<Emplacement> findAllByOrderByNomEmplacementAsc();



}