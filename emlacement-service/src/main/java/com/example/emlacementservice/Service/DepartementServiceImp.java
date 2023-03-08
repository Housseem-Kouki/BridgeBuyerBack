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
public class DepartementServiceImp implements IDepartementService {

    DepartementRepository departementRepository;
    AdresseDepartementRepository adresseDepartementRepository;

    @Override
    public Departement addDepartement(Departement Departement) {
        return departementRepository.save(Departement);
    }

    @Override
    public Departement updateDepartement(Departement Departement) {
        return departementRepository.save(Departement);
    }

    @Override
    public void deleteDepartement(int id) {
        departementRepository.deleteById(id);
    }

    @Override
    public Departement getDepartementById(int id) {
        return departementRepository.findById(id).orElse(null);
    }

    @Override
    public List<Departement> getAllDepartements() {
        return departementRepository.findAll();
    }

	@Override
	public Departement getByNomDepartement(String nomDepartement) {
		// TODO Auto-generated method stub
		return departementRepository.findByNomDepartement(nomDepartement);
		
	}



	@Override
	public Departement getDepartByNomDepart(String emplacementDepartement) {
		// TODO Auto-generated method stub
		return departementRepository.findByNomDepartement(emplacementDepartement);
	}
    @Override
    public List<Departement> ChercherParNomDepartementContainingIgnoreCase(String nomDepartement) {
        // TODO Auto-generated method stub
        return departementRepository.findByNomDepartementContainingIgnoreCase(nomDepartement);
    }
}

