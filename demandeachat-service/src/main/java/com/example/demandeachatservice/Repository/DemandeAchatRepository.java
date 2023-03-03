package com.example.demandeachatservice.Repository;

import com.example.demandeachatservice.Entities.DemandeAchat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DemandeAchatRepository extends JpaRepository<DemandeAchat, Integer> {
    DemandeAchat findByEtatdemandeachat(@Param("etat") int etat );
    @Query("select d from DemandeAchat d , User u where d.acheteur.idUser=u.idUser")
    List<DemandeAchat> findDemandeAchatsByAcheteur(@Param("idUser") int idUser) ;
}