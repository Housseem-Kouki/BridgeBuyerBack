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
public class DemandeAchat implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int iddemandeachat ;
    private int etatdemandeachat;
    private float budget ;

    @ManyToOne
    private User acheteur;

    @ManyToMany(mappedBy = "demandeAchats" ,cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private Set<Article> articles;

    @JsonIgnore
    @OneToOne(mappedBy = "demandeAchat" , cascade = CascadeType.ALL)
    private AppelOffre appelOffre;
}
