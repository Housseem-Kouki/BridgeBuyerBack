package com.example.appeloffreservice.Entities;

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
public class AppelOffre implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAppelOffre ;
    private String reference;
    @Temporal(TemporalType.DATE)
    private Date dateCreationC;
    private int etat; //2 en cours 1 expire 3 ferme
    @Temporal(TemporalType.DATE)
    private Date dateLimite;
    @Temporal(TemporalType.DATE)
    private Date datePayment;
    private double prixInitiale;
    private String commentaire;


    @OneToOne
    private DemandeAchat demandeAchat;

    @OneToOne
    private Offre offre;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "appelOffre")
    @JsonIgnore
    private Set<DevisFourniseur> ListDevisFourniseurs;


}
