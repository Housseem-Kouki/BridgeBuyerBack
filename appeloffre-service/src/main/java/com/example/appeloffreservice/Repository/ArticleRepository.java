package com.example.appeloffreservice.Repository;



import com.example.appeloffreservice.Entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
}