package com.example.emlacementservice.Service;

import com.example.emlacementservice.Entities.Reclamation;

import java.util.List;

public interface ReclamationService {

	public Reclamation updateReclamation(Reclamation reclamation);

	public void deleteReclamation(Long id);

	public List<Reclamation> getAll();

	public Reclamation findById(Long id);

	public int NmbreReclamation();

	Reclamation AddReclamation(Integer id, Reclamation com);

	Reclamation affecterUserAReclamation(Long idR, Integer id);
}
