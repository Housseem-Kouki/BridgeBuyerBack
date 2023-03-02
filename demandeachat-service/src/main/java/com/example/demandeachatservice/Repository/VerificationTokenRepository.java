package com.example.demandeachatservice.Repository;


import com.example.demandeachatservice.Entities.User;
import com.example.demandeachatservice.Entities.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Integer> {
    VerificationToken findByToken(String token);

    VerificationToken findByUser(User user);
}
