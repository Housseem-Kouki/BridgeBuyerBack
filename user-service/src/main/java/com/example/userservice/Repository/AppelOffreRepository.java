package com.example.userservice.Repository;



import com.example.userservice.Entities.AppelOffre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AppelOffreRepository extends JpaRepository<AppelOffre, Integer> {
    @Query("SELECT idAppelOffre FROM AppelOffre ")
    public List<Integer> listdesIds();
}