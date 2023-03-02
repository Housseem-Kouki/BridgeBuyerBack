package com.example.commandeservice.Entities;

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
public class NatureArticle implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idnaturearticle;
    private String nomanature ;
    private String descriptionnature ;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "natureArticle")
    @JsonIgnore
    private Set<Article> articles;

}
