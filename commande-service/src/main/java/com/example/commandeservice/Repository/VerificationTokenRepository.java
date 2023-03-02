package com.example.commandeservice.Repository;


import com.example.commandeservice.Entities.User;
import com.example.commandeservice.Entities.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Integer> {
    VerificationToken findByToken(String token);

    VerificationToken findByUser(User user);
}
