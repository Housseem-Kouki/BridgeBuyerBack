package com.example.livraisionservice.Repository;


import com.example.livraisionservice.Entities.User;
import com.example.livraisionservice.Entities.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Integer> {
    VerificationToken findByToken(String token);

    VerificationToken findByUser(User user);
}
