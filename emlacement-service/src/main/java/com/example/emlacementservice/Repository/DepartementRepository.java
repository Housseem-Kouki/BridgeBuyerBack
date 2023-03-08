package com.example.emlacementservice.Repository;

import com.example.emlacementservice.Entities.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartementRepository extends JpaRepository<Departement, Integer> {
	Departement findByNomDepartement(String nomDepartement);

	@Query("SELECT d From Departement d Where d.adresseDepartement.emplacementDepartement=:emplacementDepartement")
	Departement getDepartementByEmplacement(@Param("emplacementDepartement") String emplacementDepartement);



	List<Departement> findByNomDepartementContainingIgnoreCase(String nomDepartement);
}