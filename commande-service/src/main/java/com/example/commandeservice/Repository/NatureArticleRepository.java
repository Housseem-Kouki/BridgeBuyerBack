package com.example.commandeservice.Repository;



import com.example.commandeservice.Entities.NatureArticle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NatureArticleRepository extends JpaRepository<NatureArticle, Integer> {
}