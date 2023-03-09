package com.example.emlacementservice.Service;

import com.example.emlacementservice.Entities.Reclamation;
import com.example.emlacementservice.Entities.User;
import com.example.emlacementservice.Repository.ReclamationRepository;
import com.example.emlacementservice.Repository.UserRepository;
import org.hibernate.query.criteria.internal.expression.function.CurrentDateFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.auditing.CurrentDateTimeProvider;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;


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
		Reclamation reclamation=reclamationRepository.findById(id).get();
		if (reclamation.isArchived()==true) {
			reclamationRepository.delete(reclamation);

		}


	}

	@Override
	public List<Reclamation> getAll() {

		List<Reclamation> reclamation= reclamationRepository.findAll().stream().filter(c->c.isArchived()==false).collect(Collectors.toList());
		return reclamation;
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
	public Reclamation AddReclamation(Reclamation com , Principal principal) {
		// TODO Auto-generated method stub

		User user=userRepository.findByEmail(principal.getName());//session
		com.setUser(user);
		return reclamationRepository.save(com);


	}



	@Override
	public Reclamation affecterUserAReclamation(Long idR, Integer idU) {
		Reclamation com=reclamationRepository.findById(idR).get();
		User us=userRepository.findById(idU).get();
		com.setUser(us);

		return reclamationRepository.save(com);
	}

	@Override
	public void archiverReclamation(Long id) {
		Reclamation reclamation=reclamationRepository.findById(id).get();
		reclamation.setArchived(true);
		reclamationRepository.save(reclamation);

	}


}

