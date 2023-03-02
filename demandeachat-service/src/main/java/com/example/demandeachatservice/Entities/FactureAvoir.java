package com.example.demandeachatservice.Entities;

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
public class FactureAvoir implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFactureAvoir ;
    private float montantTotal;
    private String etat;
    private boolean archive;

    @OneToOne
    private BonRetour bonRetour;

}
