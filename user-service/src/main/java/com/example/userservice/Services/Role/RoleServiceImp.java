package com.example.userservice.Services.Role;


import com.example.userservice.Entities.Privilege;
import com.example.userservice.Entities.Role;
import com.example.userservice.Entities.User;
import com.example.userservice.Repository.PrivilegeRepository;
import com.example.userservice.Repository.RoleRepository;
import com.example.userservice.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
@Transactional
public class RoleServiceImp implements IRoleService {

RoleRepository roleRepository;
PrivilegeRepository privilegeRepository;
UserRepository userRepository;

    @Override
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    @Transactional
    public Role updateRole(Role role) {
        Set<Privilege> managedPrivileges = new HashSet<>();
        for (Privilege p : role.getPrivileges()){
            Privilege managedPrivilege = privilegeRepository.findById(p.getIdPrivilege()).orElse(null);
            System.out.println(managedPrivilege.getPrivilegeName());
            managedPrivilege.getRoles().add(role);
            managedPrivileges.add(managedPrivilege);
            //privilegeRepository.save(managedPrivilege);
        }
        role.setPrivileges(null);

        role.setPrivileges(managedPrivileges);
        /*
        Set<Privilege> privileges = role.getPrivileges();
        Set<Privilege> managedPrivileges = new HashSet<>();
        for (Privilege privilege : privileges) {
            Privilege managedPrivilege = privilegeRepository.findById(privilege.getIdPrivilege()).orElse(null);
            managedPrivilege.getRoles().add(role);
            managedPrivileges.add(managedPrivilege);
            privilegeRepository.save(managedPrivilege);
        }
        */

       // role.setPrivileges(managedPrivileges);
        return roleRepository.save(role);
    }

    @Override
    public void deleteRole(int id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Role getRoleById(int id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    @Transactional
    public Role AddRoleWithPrivilege(Role role) {
        Set<Privilege> privileges = role.getPrivileges();
        Set<Privilege> managedPrivileges = new HashSet<>();
        for (Privilege privilege : privileges) {
            Privilege managedPrivilege = privilegeRepository.findById(privilege.getIdPrivilege()).orElse(null);
            managedPrivilege.getRoles().add(role);
            managedPrivileges.add(managedPrivilege);
        }
        role.setPrivileges(managedPrivileges);
        return roleRepository.save(role);
        }


    @Override
    public Boolean AffectRoleToPrivilege(int idRole , int idPrivilege){
       boolean value;
        Role role = roleRepository.findById(idRole).orElse(null);
        Privilege privilege = privilegeRepository.findById(idPrivilege).orElse(null);
        if (role.getPrivileges() == null) {
            role.setPrivileges(new HashSet<>());

        }

       value = role.getPrivileges().add(privilege);
        return value;

    }

    @Override
    public List<User> getListUsersByIdRole(int idRole) {
        List<User> users = userRepository.findUsersByRoleIdRole(idRole);
        return users;
    }

    @Override
    public List<Privilege> getListPrivilégesByIdRole(int idRole) {
        List<Privilege> privileges = privilegeRepository.findPrivilegesByRoleId(idRole);
        return privileges;
    }


}
