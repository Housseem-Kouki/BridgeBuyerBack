package com.example.userservice.Repository;

import com.example.userservice.Entities.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PrivilegeRepository extends JpaRepository<Privilege, Integer> {
    @Query("SELECT r.privileges FROM Role r WHERE r.idRole = :roleId")
    List<Privilege>  findPrivilegesByRoleId(@Param("roleId") int roleId);

    @Query("SELECT p FROM Privilege p JOIN p.roles r WHERE r.id = :roleId")
    List<Privilege> findByRoleId(@Param("roleId") int roleId);
}