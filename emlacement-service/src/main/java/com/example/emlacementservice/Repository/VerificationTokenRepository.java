package com.example.emlacementservice.Repository;


import com.example.emlacementservice.Entities.User;
import com.example.emlacementservice.Entities.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Integer> {
    VerificationToken findByToken(String token);

    VerificationToken findByUser(User user);
}
