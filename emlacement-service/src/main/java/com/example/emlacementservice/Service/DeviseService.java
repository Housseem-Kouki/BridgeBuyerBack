package com.example.emlacementservice.Service;

import com.example.emlacementservice.Entities.Devise;
import com.example.emlacementservice.Entities.Emplacement;
import com.example.emlacementservice.Repository.DeviseRepository;
import com.example.emlacementservice.Repository.EmplacementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class DeviseService implements IDeviseService{
    @Autowired
    DeviseRepository DeviseRepository;
    @Autowired
    EmplacementRepository emplacementRepository ;


    @Override
    public Devise addDevise(Devise Devise) {
        return DeviseRepository.save(Devise);
    }

    @Override
    public Devise updateDevise(Devise Devise) {
        return DeviseRepository.save(Devise);
    }

    @Override
    public void deleteDevise(int id) {
        DeviseRepository.deleteById(id);
    }

    @Override
    public Devise getDeviseById(int id) {
        return DeviseRepository.findById(id).orElse(null);
    }

    @Override
    public List<Devise> getAllDevises() {
        return DeviseRepository.findAll();
    }

    @Override
    public Devise getByNomDevise(String nomDevise) {
        // TODO Auto-generated method stub
        return DeviseRepository.findByNomDevise(nomDevise);

    }
    @Override
    public void ActiverDevise(String nomd)
    {
        Devise d = getByNomDevise(nomd);
        d.setEtat(Boolean.TRUE);
        DeviseRepository.save(d);

    }
    @Override
    public void DesactiverDevise(String nomd)
    {
        Devise d = getByNomDevise(nomd);
        d.setEtat(Boolean.FALSE);
        DeviseRepository.save(d);

    }
    @Override
    public Devise affecterDeviseToEmp(Devise d, Integer idE) {
        Emplacement emplacement=emplacementRepository.findById(idE).orElse(null);



       return DeviseRepository.save(d);

    }


}
