package com.example.emlacementservice.Service;


import com.example.emlacementservice.Entities.AdresseDepartement;

import java.util.List;

public interface IAdresseDepartementService {
    public AdresseDepartement addAdresseDepartement(AdresseDepartement ad);
    public AdresseDepartement updateAdresseDepartement(AdresseDepartement ad);
    public void deleteAdresseDepartement(int id);
    public AdresseDepartement getAdresseDepartementById(int id);
    public List<AdresseDepartement> getAllAdresseDepartements();
}
