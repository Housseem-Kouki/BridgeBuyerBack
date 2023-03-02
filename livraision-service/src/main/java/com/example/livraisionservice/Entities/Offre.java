package com.example.livraisionservice.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Offre implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idOffre ;
    private int etat; // 1 accepte 2 en attente 3 refusee
    private String commentaire;
    @Temporal(TemporalType.DATE)
    private Date datecreationO;
    @Temporal(TemporalType.DATE)
    private Date dateValidation;
    private Double prixOffre;

    @JsonIgnore
    @OneToOne(mappedBy = "offre" , cascade = CascadeType.ALL)
    private AppelOffre appelOffre;

    @JsonIgnore
    @OneToOne(mappedBy = "offre" , cascade = CascadeType.ALL)
    private Commande commande;

}
