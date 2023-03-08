package com.example.emlacementservice.Service;


import com.example.emlacementservice.Entities.AdresseDepartement;
import com.example.emlacementservice.Entities.Departement;

import java.util.List;

public interface IDepartementService {
    public Departement addDepartement(Departement d);
    public Departement updateDepartement(Departement d);
    public void deleteDepartement(int id);
    public Departement getDepartementById(int id);
    public List<Departement> getAllDepartements();
    Departement getByNomDepartement(String nomDepartement);

    //Departement affecterAdresseDepartementToDepartement(AdresseDepartement ad, Integer idD);

    // Departement affecterAdresseDepartementToDepartement(AdresseDepartement ad, Integer idD);
    Departement getDepartByNomDepart(String emplacementDepartement);


    List<Departement> ChercherParNomDepartementContainingIgnoreCase(String nomDepartement);
}
