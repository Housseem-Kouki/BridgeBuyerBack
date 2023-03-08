package com.example.livraisionservice.Repository;



import com.example.livraisionservice.Entities.FactureAvoir;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FactureAvoirRepository extends JpaRepository<FactureAvoir, Integer> {
    @Query(value = "select fa from FactureAvoir fa where concat(fa.idFactureAvoir,fa.montantTotal,fa.archive) like %?1% ")
    List<FactureAvoir> recherche(String keyword);
}