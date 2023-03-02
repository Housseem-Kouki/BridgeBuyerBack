package com.example.demandeachatservice.Services.DemandeAchat.Unite;

import com.example.demandeachatservice.Entities.Article;
import com.example.demandeachatservice.Entities.UniteArticle;
import com.example.demandeachatservice.Repository.ArticleRepository;
import com.example.demandeachatservice.Repository.UniteArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor

public class UniteService implements IUniteService{
    UniteArticleRepository uniteRepository ;
    ArticleRepository articleRepository;

    @Override
    public UniteArticle addUnite(UniteArticle u) {
        return uniteRepository.save(u);
    }

    @Override
    public UniteArticle updateUnite(UniteArticle u) {
        return uniteRepository.save(u);
    }

    @Override
    public void deleteUnite(int id) {
        uniteRepository.deleteById(id);

    }

    @Override
    public UniteArticle getUniteById(int id) {
        return uniteRepository.findById(id).orElse(null);
    }

    @Override
    public List<UniteArticle> getAllUnite() {
        return uniteRepository.findAll();
    }

    @Override
    public UniteArticle addAndAssignUniteToArticle(UniteArticle unite, int idArticle) {
        Article article = articleRepository.findById(idArticle).orElse(null);

        article.setUniteArticle(unite);
        return uniteRepository.save(unite);
    }

    @Override
    public void AssignUniteToArticle(int idUnite, int idArticle) {
        Article article = articleRepository.findById(idArticle).orElse(null);
        UniteArticle uniteArticle=uniteRepository.findById(idUnite).orElse(null);
        article.setUniteArticle(uniteArticle);

        uniteRepository.save(uniteArticle);
    }

}
