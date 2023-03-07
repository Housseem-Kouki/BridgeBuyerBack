package com.example.appeloffreservice.Repository;



import com.example.appeloffreservice.Entities.Article;
import com.example.appeloffreservice.Entities.DevisFourniseur;
import com.example.appeloffreservice.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
    List<Article> findArticleByPrixestime (Article article);

    List<Article> findArticleByNomarticle (@Param("nomarticle") String  nomarticle);

}