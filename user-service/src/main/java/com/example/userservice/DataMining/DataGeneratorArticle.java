package com.example.userservice.DataMining;

import com.example.userservice.Entities.*;
import com.example.userservice.Repository.DemandeAchatRepository;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Component
@AllArgsConstructor
public class DataGeneratorArticle {
    private final Faker faker = new Faker();
    DemandeAchatRepository demandeAchatRepository;
    public Article generate() {
        Article article = new Article();
        article.setDescriptionarticle(faker.lorem().sentence());
        article.setNomarticle(faker.commerce().productName());
        article.setPrixestime((float) faker.number().randomDouble(2, 1, 1000));
        article.setQuantite(faker.number().numberBetween(0, 1000));
        double remise = 0.2 + (0.9 - 0.2) * faker.random().nextDouble();

        article.setPrixestimeAvecRemise((float) (article.getPrixestime()*remise));
        //article.setPrixestimeAvecRemise
        NatureArticle natureArticle = new NatureArticle();
        natureArticle.setIdnaturearticle(faker.number().numberBetween(1,4));
        article.setNatureArticle(natureArticle);

        UniteArticle uniteArticle = new UniteArticle();
        uniteArticle.setIdunitearticle(faker.number().numberBetween(1,4));
        article.setUniteArticle(uniteArticle);



        //DemandeAchat demandeAchat = new DemandeAchat();
        //demandeAchat.setIddemandeachat(faker.number().numberBetween(1100 , 1110));
     //  DemandeAchat demandeAchat1 = demandeAchatRepository.findById(faker.number().numberBetween(1095 , 1100)).orElse(null);
        //liste des id de de    mande d'achat

        // Créer une instance de Random
        Random random = new Random();
        List<Integer> ids = demandeAchatRepository.listdesIds();
        int randomIndex = random.nextInt(ids.size());
        int randomId = ids.get(randomIndex);
        DemandeAchat demandeAchat = demandeAchatRepository.findById(randomId).orElse(null);

        //demandeAchatRepository.save(demandeAchat);
        Set<DemandeAchat> demandeAchats = new HashSet<>();
        demandeAchats.add(demandeAchat);
        //demandeAchat1.getArticles().add(article);
        //demandeAchatRepository.save(demandeAchat1);
        article.setDemandeAchats(demandeAchats);




        return article;
    }




}
