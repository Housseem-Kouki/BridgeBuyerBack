package com.example.demandeachatservice.Repository;



import com.example.demandeachatservice.Entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
}