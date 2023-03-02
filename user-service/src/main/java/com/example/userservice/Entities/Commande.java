package com.example.userservice.Entities;

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
public class Commande implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCommande ;

    @OneToOne
    private  Offre offre;

    @ManyToOne
    private Paiment paiment;

    @OneToOne(mappedBy = "commande" , cascade = CascadeType.ALL)
    @JsonIgnore
    private Facture facture;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "commande")
    @JsonIgnore
    private Set<Livraison> livraisions;

    private double prixTotal;
    private int etatCommande;
}
