package com.example.livraisionservice.Repository;



import com.example.livraisionservice.Entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
}