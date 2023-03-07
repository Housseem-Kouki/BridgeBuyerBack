package com.example.demandeachatservice.Services.DemandeAchat.Article;

import com.example.demandeachatservice.Entities.Article;
import com.example.demandeachatservice.Entities.DemandeAchat;
import com.example.demandeachatservice.Repository.ArticleRepository;
import com.example.demandeachatservice.Repository.DemandeAchatRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor

public class ArticleService implements IArticleService {
    ArticleRepository articleRepository ;
    DemandeAchatRepository demandeAchatRepository;

    @Override
    public Article addArticle(Article a) {

        return articleRepository.save(a);
    }

    @Override
    public Article updateArticle(Article a) {
        return articleRepository.save(a);
    }

    @Override
    public void deleteArticle(int id) {
        articleRepository.deleteById(id);
    }

    @Override
    public Article getArticleById(int id) {
        return articleRepository.findById(id).orElse(null);
    }

    @Override
    public List<Article> getAllArticle() {
        return articleRepository.findAll();
    }

    @Override
    public void assignArticleToDemandeAchat(int idArticle, int idDemande) {
        Article article = articleRepository.findById(idArticle).orElse(null);
        DemandeAchat demandeAchat = demandeAchatRepository.findById(idDemande).orElse(null);
     article.getDemandeAchats().add(demandeAchat);
       articleRepository.save(article) ;
    }

    @Override
    public List<Article> SearchMultiple(String key) {
        if (key.equals(""))
        {
            return (List<Article>) articleRepository.findAll();
        }else
        {
            return articleRepository.rech(key);
        }
    }
}
