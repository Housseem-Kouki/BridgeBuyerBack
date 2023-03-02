package com.example.demandeachatservice.Repository;



import com.example.demandeachatservice.Entities.NatureArticle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NatureArticleRepository extends JpaRepository<NatureArticle, Integer> {
}