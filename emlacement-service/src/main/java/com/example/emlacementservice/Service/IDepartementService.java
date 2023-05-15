package com.example.emlacementservice.Service;


import com.example.emlacementservice.Entities.Departement;

import java.util.List;

public interface IDepartementService {
    public Departement addDepartement(Departement d , Integer idE);

    public Departement updateDepartement(Departement d);

    public void deleteDepartement(int id);

    public Departement getDepartementById(int id);

    public List<Departement> getAllDepartements();

    Departement getByNomDepartement(String nomDepartement);

    void affecterAdresseDepartementToDepartement(Integer idD, Integer idAD);

    Departement getDepartByNomDepart(String emplacementDepartement);

    List<Departement> ChercherParNomDepartementContainingIgnoreCase(String nomDepartement);

}
