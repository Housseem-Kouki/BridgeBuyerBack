package com.example.demandeachatservice.Services.DemandeAchat.Article;

import com.example.demandeachatservice.Entities.Article;

import java.util.List;

public interface IArticleService {
    public Article addArticle(Article a);
    public Article updateArticle(Article a);
    public void deleteArticle(int id);
    public Article getArticleById(int id);
    public List<Article> getAllArticle();

    public void assignArticleToDemandeAchat(int idArticle, int idDemande) ;
    public List<Article> SearchMultiple(String key) ;
    public List<Article> findByCriteria(String nomarticle, String descriptionarticle, Float prixestime, Integer quantite) ;
}
