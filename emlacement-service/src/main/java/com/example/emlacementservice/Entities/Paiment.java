package com.example.emlacementservice.Entities;


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
public class Paiment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPaiment ;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "paiment")

    private Set<Commande> commandes;



    private String token;
    private double amount;

    @Enumerated(EnumType.STRING)
    public Currency currency;

    @Temporal(TemporalType.DATE)
    private Date datePayment;


}