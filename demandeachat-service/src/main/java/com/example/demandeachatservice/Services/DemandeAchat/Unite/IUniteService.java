package com.example.demandeachatservice.Services.DemandeAchat.Unite;

import com.example.demandeachatservice.Entities.UniteArticle;

import java.util.List;

public interface IUniteService {
    public UniteArticle addUnite(UniteArticle u);
    public UniteArticle updateUnite(UniteArticle u);
    public void deleteUnite(int id);
    public UniteArticle getUniteById(int id);
    public List<UniteArticle> getAllUnite();
    UniteArticle addAndAssignUniteToArticle(UniteArticle unite, int idArticle);
    void AssignUniteToArticle(int idUnite, int idArticle) ;
}
