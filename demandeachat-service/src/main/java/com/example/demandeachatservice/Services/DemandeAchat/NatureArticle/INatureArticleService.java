package com.example.demandeachatservice.Services.DemandeAchat.NatureArticle;

import com.example.demandeachatservice.Entities.NatureArticle;

import java.util.List;

public interface INatureArticleService {
    public NatureArticle addNatureArticle(NatureArticle n);
    public NatureArticle updateNatureArticle(NatureArticle n);
    public void deleteNatureArticle(int id);
    public NatureArticle getNatureArticleById(int id);
    public List<NatureArticle> getAllNatureArticle();
    NatureArticle addAndAssignNatureArticleToArticle(NatureArticle nature, int idArticle);
    public void AssignNatureArticleToArticle(int idNature, int idArticle) ;

}
