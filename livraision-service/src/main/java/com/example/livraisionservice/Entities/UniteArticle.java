package com.example.livraisionservice.Entities;

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
public class UniteArticle implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idunitearticle ;
    private  String nomunite ;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "uniteArticle")
    @JsonIgnore
    private Set<Article> articles;
}
