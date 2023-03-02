package com.example.commandeservice.Repository;

import com.example.commandeservice.Entities.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege, Integer> {
}