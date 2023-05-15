package com.example.userservice.Repository;

import com.example.userservice.Entities.Privilege;
import com.example.userservice.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query("SELECT p.roles FROM Privilege p WHERE p.idPrivilege = :idPrivilege")
    List<Role> findRolesByPrivilegeId(@Param("idPrivilege") int idPrivilege);
}