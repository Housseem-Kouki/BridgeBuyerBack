package com.example.appeloffreservice.Repository;



import com.example.appeloffreservice.Entities.AppelOffre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppelOffreRepository extends JpaRepository<AppelOffre, Integer> {
    @Query(value = "select a from AppelOffre a where a.reference like %?1% ")
    List<AppelOffre> rech(String keyword);
    List<AppelOffre> findAppelOffreByEtat( @Param("etat") int etat);
}