package com.example.appeloffreservice.Repository;


import com.example.appeloffreservice.Entities.User;
import com.example.appeloffreservice.Entities.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Integer> {
    VerificationToken findByToken(String token);

    VerificationToken findByUser(User user);
}
