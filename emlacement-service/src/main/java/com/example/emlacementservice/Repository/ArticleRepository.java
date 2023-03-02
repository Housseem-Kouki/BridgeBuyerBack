package com.example.emlacementservice.Repository;



import com.example.emlacementservice.Entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
}