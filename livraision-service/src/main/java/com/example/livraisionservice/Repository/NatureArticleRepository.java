package com.example.livraisionservice.Repository;


import com.example.livraisionservice.Entities.NatureArticle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NatureArticleRepository extends JpaRepository<NatureArticle, Integer> {
}