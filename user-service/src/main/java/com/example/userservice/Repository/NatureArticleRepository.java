package com.example.userservice.Repository;


import com.example.userservice.Entities.NatureArticle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NatureArticleRepository extends JpaRepository<NatureArticle, Integer> {
}