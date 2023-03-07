package com.example.emlacementservice.Service;


import com.example.emlacementservice.Entities.AdresseDepartement;
import com.example.emlacementservice.Repository.AdresseDepartementRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdresseDepartementServiceImp implements IAdresseDepartementService {

    AdresseDepartementRepository AdresseDepartementRepository;

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
}
