package com.example.emlacementservice.Service;

import java.util.List;

import com.example.emlacementservice.Entities.Emplacement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IEmplacementService {
    Emplacement addEmplacement(Emplacement e , int idA);

    Emplacement updateEmplacement(Emplacement e,int id);

    void deleteEmplacement(int id);

    Emplacement getEmplacementById(int id);

    List<Emplacement> getAllEmplacements();

    void affecterEmplacementToDepartement(Integer idE, Integer idD);

    List<Emplacement> findAllEmplacementTrié();



    void supprimerEmplacementsNonAffectes();

}
