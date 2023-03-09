package com.example.demandeachatservice.Repository;

import com.example.demandeachatservice.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByEmail(String email);
}