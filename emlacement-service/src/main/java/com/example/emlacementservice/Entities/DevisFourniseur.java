package com.example.emlacementservice.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DevisFourniseur implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDevisFourniseur ;
    private Double Ptotal;
    private Double PTSansRemise;
    private int etat;
    private int periodeValidite;
    private int delaiLivraison;
    private int remise;
    private String commentaire;
    private boolean disopnible;
    private double pourcentageRemise;
    private double prixInitiale;

    @ManyToOne
    private AppelOffre appelOffre;

    @ManyToOne
    private User fourniseur;
}
