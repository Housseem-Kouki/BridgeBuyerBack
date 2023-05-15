package com.example.userservice.DataMining;

import com.example.userservice.Entities.*;
import com.example.userservice.Repository.ArticleRepository;
import com.example.userservice.Repository.UserRepository;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Component
@AllArgsConstructor
public class DataGeneratorDemandeAchat {
    private final Faker faker = new Faker();
    ArticleRepository articleRepository;
    UserRepository userRepository;

    public DemandeAchat generate() {
        DemandeAchat demandeAchat = new DemandeAchat();


        demandeAchat.setBudget(faker.number().randomNumber());

        demandeAchat.setEtatdemandeachat((int) faker.number().randomDouble(2, 1, 1000));
        List<Integer> ids = userRepository.listdesIds();
        Random random = new Random();
        int randomIndex = random.nextInt(ids.size());
        int randomId = ids.get(randomIndex);
        User acheteur = userRepository.findById(randomId).orElse(null);
        demandeAchat.setAcheteur(acheteur);


        List<Integer> idsa = articleRepository.listdesIds();
        int randomIndexa = random.nextInt(idsa.size());
        int randomIda = idsa.get(randomIndexa);
        Article article = articleRepository.findById(randomIda).orElse(null);

        //demandeAchatRepository.save(demandeAchat);
        Set<Article> articles = new HashSet<>();
        articles.add(article);
        //demandeAchat1.getArticles().add(article);
        //demandeAchatRepository.save(demandeAchat1);
        demandeAchat.setArticles(articles);

        return demandeAchat;
    }




}
