package com.example.demandeachatservice.Services.DemandeAchat.Article;

import com.example.demandeachatservice.Entities.Article;
import com.example.demandeachatservice.Entities.DemandeAchat;
import com.example.demandeachatservice.Repository.ArticleRepository;
import com.example.demandeachatservice.Repository.DemandeAchatRepository;
import com.example.demandeachatservice.Services.DemandeAchat.SmsService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        if (demandeAchat.getArticles() == null) {
            demandeAchat.setArticles(new HashSet<>());

        }
        demandeAchat.getArticles().add(article);
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

   /* public List<User> getUsersByLastName(String lastName) {
        return userRepository.findAll().stream()
                .filter(user -> user.getLastName().equals(lastName))
                .collect(Collectors.toList());
    }
    @Override
    public List<Article> serchDyna() {
        return articleRepository.findAll(Sort.by("nomarticle")
                        .by("descriptionarticle")
                        .by("prixestime")
                        .by("quantite"))
                .stream()


                .collect(Collectors.toList());
    }*/
   @Override
   public List<Article> findByCriteria(String nomarticle, String descriptionarticle, Float prixestime, Integer quantite) {
       return articleRepository.findAll()
               .stream()
               .filter(article -> nomarticle == null || article.getNomarticle().contains(nomarticle))
               .filter(article -> descriptionarticle == null || article.getDescriptionarticle().contains(descriptionarticle))
               .filter(article -> prixestime == null || article.getPrixestime()==prixestime)
               .filter(article -> quantite == null || article.getQuantite()==quantite)
               .collect(Collectors.toList());
   }
   }



    

