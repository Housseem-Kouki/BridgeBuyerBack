package com.example.demandeachatservice.Repository;

import com.example.demandeachatservice.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}