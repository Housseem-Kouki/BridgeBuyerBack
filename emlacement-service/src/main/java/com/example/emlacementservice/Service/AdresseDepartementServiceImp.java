package com.example.emlacementservice.Service;


import com.example.emlacementservice.Entities.AdresseDepartement;
import com.example.emlacementservice.Entities.Departement;
import com.example.emlacementservice.Repository.AdresseDepartementRepository;
import com.example.emlacementservice.Repository.DepartementRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdresseDepartementServiceImp implements IAdresseDepartementService {

    AdresseDepartementRepository AdresseDepartementRepository;
    private final DepartementRepository departementRepository;

    @Override
    public AdresseDepartement addAdresseDepartement(AdresseDepartement u) {
        return AdresseDepartementRepository.save(u);
    }

    @Override
    public AdresseDepartement updateAdresseDepartement(AdresseDepartement u) {
        return AdresseDepartementRepository.save(u);
    }

    @Override
    public void deleteAdresseDepartement(int id) {
        AdresseDepartementRepository.deleteById(id);
    }

    @Override
    public AdresseDepartement getAdresseDepartementById(int id) {
        return AdresseDepartementRepository.findById(id).orElse(null);
    }

    @Override
    public List<AdresseDepartement> getAllAdresseDepartements() {
        return AdresseDepartementRepository.findAll();
    }

    @Override
    public List<AdresseDepartement> filterByDepAndCode(String dep, String c) {
        // TODO Auto-generated method stub
        return AdresseDepartementRepository.findByDepAndCode(dep,c);
    }
    @Override
    public AdresseDepartement affecterAdresseDepartementToDepartement(AdresseDepartement ad, Integer idD) {
        Departement d=departementRepository.findById(idD).orElse(null);
        if(d.getAdresseDepartement() == null){
            d.setAdresseDepartement(ad);
        }


        return AdresseDepartementRepository.save(ad);



    }




}
