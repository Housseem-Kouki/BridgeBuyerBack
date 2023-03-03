package com.example.demandeachatservice.Services.DemandeAchat.NatureArticle;

import com.example.demandeachatservice.Entities.Article;
import com.example.demandeachatservice.Entities.NatureArticle;
import com.example.demandeachatservice.Repository.ArticleRepository;
import com.example.demandeachatservice.Repository.NatureArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor

public class NatureArticleService implements INatureArticleService{
    NatureArticleRepository natureArticleRepository ;
    ArticleRepository articleRepository ;
    @Override
    public NatureArticle addNatureArticle(NatureArticle n) {
        return natureArticleRepository.save(n);
    }

    @Override
    public NatureArticle updateNatureArticle(NatureArticle n) {
        return  natureArticleRepository.save(n);
    }

    @Override
    public void deleteNatureArticle(int id) {
        natureArticleRepository.deleteById(id);
    }

    @Override
    public NatureArticle getNatureArticleById(int id) {
        return  natureArticleRepository.findById(id).orElse(null);
    }

    @Override
    public List<NatureArticle> getAllNatureArticle() {
        return  natureArticleRepository.findAll();
    }

    @Override
    public NatureArticle addAndAssignNatureArticleToArticle(NatureArticle nature, int idArticle) {
        Article article = articleRepository.findById(idArticle).orElse(null);

        article.setNatureArticle(nature);
        return natureArticleRepository.save(nature);
    }
    @Override
    public void AssignNatureArticleToArticle(int idNature, int idArticle) {
        Article article = articleRepository.findById(idArticle).orElse(null);
        System.out.println("hhhhhhhhhhhhhhhhhhhhhh" + article.getNomarticle());
       NatureArticle nature=natureArticleRepository.findById(idNature).orElse(null);


        article.setNatureArticle(nature);
        System.out.println("hhhhhhhhhhhhhhhhhhhhhh" + article.getNatureArticle().getNomanature());

         natureArticleRepository.save(nature);
    }
}
