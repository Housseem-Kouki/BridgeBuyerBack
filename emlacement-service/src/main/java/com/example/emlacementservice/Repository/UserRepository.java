package com.example.emlacementservice.Repository;


import com.example.emlacementservice.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByEmail(String email);
    List<User> findByEmplacementIdEmplacement(Integer idEmplacement);

}