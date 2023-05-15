package com.example.appeloffreservice.Repository;

import com.example.appeloffreservice.Entities.DevisFourniseur;
import com.example.appeloffreservice.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DevisFourniseurRepository extends JpaRepository<DevisFourniseur, Integer> {
    @Query("SELECT q FROM DevisFourniseur q WHERE q.idDevisFourniseur = :id1 OR q.idDevisFourniseur = :id2")
    List<DevisFourniseur> findByIds(@Param("id1") int id1 , @Param("id2") int id2);
    DevisFourniseur findDevisFourniseursByIdDevisFourniseur(@Param(("id1"))int id1);
    List<DevisFourniseur> findDevisFourniseursByFourniseur (User user);

    // @Query("SELECT q FROM AppelOffre q WHERE q.idAppelOffre = :idAppelOffre")
    @Query("SELECT q FROM DevisFourniseur q JOIN q.appelOffre a WHERE a.idAppelOffre = :idAppelOffre")

    List<DevisFourniseur> findDevisFourniseurByIdAppeloffre(@Param("idAppelOffre")int idAppeloffre);



}