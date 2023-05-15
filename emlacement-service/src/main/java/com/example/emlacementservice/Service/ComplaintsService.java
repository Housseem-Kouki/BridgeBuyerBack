package com.example.emlacementservice.Service;

import java.util.List;

import com.example.emlacementservice.Entities.Complaints;

public interface ComplaintsService {

	void  updateComplaints(Complaints complaints , Long id);

	public void deleteComplaints(Long id);
	void archiverComplaint(Long id);

	public List<Complaints> getAll();

	public Complaints findById(Long id);

	public int NmbreComplaints();

	Complaints AddComplaints(Integer id, Complaints com);

	Complaints affecterUserAComplaints(Long idR, Integer id);
}
