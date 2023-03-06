package com.example.emlacementservice.Service;

import com.example.emlacementservice.Entities.Reclamation;
import com.example.emlacementservice.Entities.User;
import com.example.emlacementservice.Repository.ReclamationRepository;
import com.example.emlacementservice.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReclamationServiceImpl implements ReclamationService{

	@Autowired
	private ReclamationRepository reclamationRepository;
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public Reclamation updateReclamation(Reclamation reclamation) {
	
		return reclamationRepository.save(reclamation);
	}

	@Override
	public void deleteReclamation(Long id) {
		reclamationRepository.deleteById(id);
		
	}

	@Override
	public List<Reclamation> getAll() {
		return reclamationRepository.findAll();
	}

	@Override
	public Reclamation findById(Long id) {
		return reclamationRepository.findById(id).orElse(null);
	}

	@Override
	public int NmbreReclamation() {
	
		return reclamationRepository.findAll().size();
		
	}
	
	@Override
	public Reclamation AddReclamation(Integer id, Reclamation com) {
		// TODO Auto-generated method stub

		User user=userRepository.findById(id).get();
		com.setUser(user);
			return reclamationRepository.save(com);
		
		
	}

	@Override
	public Reclamation affecterUserAReclamation(Long idR, Integer idU) {
		  Reclamation com= reclamationRepository.findById(idR).get();
	        User us=userRepository.findById(idU).get();
	        com.setUser(us);

	        return reclamationRepository.save(com);
	}

}

