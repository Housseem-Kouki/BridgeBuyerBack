package com.example.appeloffreservice.Repository;


import com.example.appeloffreservice.Entities.NatureArticle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NatureArticleRepository extends JpaRepository<NatureArticle, Integer> {
}