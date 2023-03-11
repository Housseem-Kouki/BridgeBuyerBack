package com.example.emlacementservice.Service;

import com.example.emlacementservice.Entities.Devise;
import com.example.emlacementservice.Entities.Emplacement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IEmplacementService {
    public Emplacement addEmplacement(Emplacement e);
    public Emplacement updateEmplacement(Emplacement e);
    public void deleteEmplacement(int id);
    public Emplacement getEmplacementById(int id);
    public List<Emplacement> getAllEmplacements();
   public Emplacement affecterEmplacementToDepartement(Emplacement e , Integer idD);
  //public  Emplacement affecterDeviseToEmp(Devise d, Integer idE);
    Page<Emplacement> findAllEmplacementTrié(Pageable pageable);
    void supprimerEmplacementsNonAffectes();

    public Emplacement affectResponsableToUser(int idUserResponsble , int idEmplacement );


    //List<Emplacement> searchEmplacement(String key);
    //List<Emplacement> filterByDeviseAndResponsable(Devise dev, User resp);


  List<Emplacement> findByCriteria(String nomemp,String devise,String nomresp);
}
