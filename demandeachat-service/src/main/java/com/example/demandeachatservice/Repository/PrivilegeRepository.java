package com.example.demandeachatservice.Repository;

import com.example.demandeachatservice.Entities.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege, Integer> {
}