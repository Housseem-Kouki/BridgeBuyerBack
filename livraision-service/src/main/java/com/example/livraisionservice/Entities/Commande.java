package com.example.livraisionservice.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
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
    @JsonIgnore
    private Paiment paiment;

    @OneToOne(mappedBy = "commande" , cascade = CascadeType.ALL)
    private Facture facture;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "commande")
    @JsonIgnore
    private Set<Livraison> livraisions;

    private double prixTotalHorsTaxe;
    private double prixTotalAvecTaxe;
    private int etatCommande; // 0 etat non facture 1 etat facture  2  etat payer
    @Temporal(TemporalType.DATE)
    private Date creationCommande;
    private boolean archive;


}