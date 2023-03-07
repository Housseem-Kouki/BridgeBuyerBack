package com.example.demandeachatservice.Repository;



import com.example.demandeachatservice.Entities.Article;
import com.example.demandeachatservice.Entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
    @Query("select u.comments from Article u where u=:article")
    List<Comment> getAllCommentsByArticle(@Param("article") Article article);
    @Query(value = "select a from Article a where concat(a.nomarticle,a.descriptionarticle,a.prixestime,a.quantite) like %?1% ")

    List<Article> rech(String keyword);
    List<Article> findArticleByPrixestime(@Param("prixestime") float prixestime) ;


    List<Article> findAllByNomarticleAndDescriptionarticleAndPrixestime(String nomarticle, String descriptionarticle, Float prixestime);


}