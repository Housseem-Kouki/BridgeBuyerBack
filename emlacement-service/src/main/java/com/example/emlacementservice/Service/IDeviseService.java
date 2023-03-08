package com.example.emlacementservice.Service;

import com.example.emlacementservice.Entities.Devise;

import java.util.List;

public interface IDeviseService {
    public Devise addDevise(Devise dev);
    public Devise updateDevise(Devise dev);
    public void deleteDevise(int id);
    public Devise getDeviseById(int id);
    public List<Devise> getAllDevises();
    public Devise getByNomDevise(String nom) ;

    void ActiverDevise(String nomd);

    void DesactiverDevise(String nomd);

    Devise affecterDeviseToEmp(Devise d, Integer idE);
}
