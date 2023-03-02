package com.example.demandeachatservice.Entities;

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
public class Paiment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPaiment ;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "paiment")
    private Set<Commande> commandes;

    private String modePaiement;

    private String token;
    private double amount;
    private String description;
    public String currency;

}
