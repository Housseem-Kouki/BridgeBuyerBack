package com.example.userservice.Repository;



import com.example.userservice.Entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
    @Query("SELECT idarticle FROM Article ")
    public List<Integer> listdesIds();
}