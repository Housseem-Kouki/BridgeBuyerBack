package com.example.emlacementservice.Service;

import java.util.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.emlacementservice.Entities.Complaints;
import com.example.emlacementservice.Entities.User;
import com.example.emlacementservice.Repository.ComplaintsRepository;
import com.example.emlacementservice.Repository.UserRepository;

@Service
public class ComplaintsServiceImpl implements ComplaintsService{
	@Autowired
	private ComplaintsRepository complaintsRepository;
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public void updateComplaints(Complaints complaints , Long id ) {
		
		Complaints tawa=complaintsRepository.findById(id).get();
		complaints.setComplaintDate(new Date());
		complaints.setUser(tawa.getUser());
	   complaints.setId(id);
		 complaintsRepository.save(complaints);
	}

	@Override
	public void deleteComplaints(Long id) {
		Complaints complaints=complaintsRepository.findById(id).get();
	      
			complaintsRepository.delete(complaints);
			
		
	
		
	}

	@Override
	public List<Complaints> getAll() {
		
		List<Complaints> complaints= complaintsRepository.findAll().stream().filter(c->c.isArchived()==false).collect(Collectors.toList());
		return complaints;
	}

	@Override
	public Complaints findById(Long id) {
		return complaintsRepository.findById(id).orElse(null);
	}

	@Override
	public int NmbreComplaints() {
	
		return complaintsRepository.findAll().size();
		
	}
	
	@Override
	public Complaints AddComplaints(Integer id,Complaints com) {
		// TODO Auto-generated method stub
		com.setComplaintDate(new Date());
		User user=userRepository.findById(id).get();
		com.setUser(user);
			return complaintsRepository.save(com);
		
		
	}

	@Override
	public Complaints affecterUserAComplaints(Long idR, Integer idU) {
		  Complaints com=complaintsRepository.findById(idR).get();
	        User us=userRepository.findById(idU).get();
	        com.setUser(us);

	        return complaintsRepository.save(com);
	}

	@Override
	public void archiverComplaint(Long id) {
		Complaints complaints=complaintsRepository.findById(id).get();
		complaints.setArchived(true);
		complaintsRepository.save(complaints);
		
	}

}

