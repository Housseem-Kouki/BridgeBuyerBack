package com.example.userservice.Repository;


import com.example.userservice.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByEmail(String email);

    @Query("SELECT idUser FROM User ")
    public List<Integer> listdesIds();

    public  List<User> findUsersByRoleIdRole(int idRole);
}