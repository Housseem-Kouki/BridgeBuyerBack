package com.example.emlacementservice.Service;

import com.example.emlacementservice.Entities.Emplacement;

import java.util.List;

public interface IEmplacementService {
    public Emplacement addEmplacement(Emplacement e);
    public Emplacement updateEmplacement(Emplacement e);
    public void deleteEmplacement(int id);
    public Emplacement getEmplacementById(int id);
    public List<Emplacement> getAllEmplacements();
   public  void affecterEmplacementToDepartement(Integer idE , Integer idD);

   
}
