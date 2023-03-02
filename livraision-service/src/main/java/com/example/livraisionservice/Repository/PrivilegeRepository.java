package com.example.livraisionservice.Repository;

import com.example.livraisionservice.Entities.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege, Integer> {
}