package com.example.commandeservice.Repository;



import com.example.commandeservice.Entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
}