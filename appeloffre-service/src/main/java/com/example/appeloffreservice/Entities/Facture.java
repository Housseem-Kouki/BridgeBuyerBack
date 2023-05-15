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
public class Facture implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFacture ;

    private String description;
    @JsonIgnore
    @OneToOne(mappedBy = "adresseExpedition" , cascade = CascadeType.ALL)
    private  Emplacement emplacement;

    @OneToOne
    @JsonIgnore
    private  Commande commande;

    @ManyToMany(mappedBy = "factures" ,cascade = CascadeType.ALL , fetch = FetchType.EAGER)

    private Set<ChargeFinanciere> listChargeFinancieres;
    @ManyToMany(mappedBy = "factures" ,cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private  Set<Taxe> taxes;

    private double montantFacture;

    @Temporal(TemporalType.DATE)
    private Date dateFacture;
    private boolean archive;
}