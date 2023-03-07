package com.example.appeloffreservice.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Article implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idarticle ;
    private String nomarticle ;
    private String descriptionarticle ;
    private float prixestime ;
    private int quantite ;
    private float prixestimeAvecRemise  ;


    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<DemandeAchat> demandeAchats;

    @ManyToOne
    private UniteArticle uniteArticle;

    @ManyToOne
    private NatureArticle natureArticle;

    @OneToMany(mappedBy = "article")
    @JsonIgnore
    private Set<Comment> comments;



}
