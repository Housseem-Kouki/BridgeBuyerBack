package com.example.demandeachatservice.Entities;

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
public class BonReception implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBonReception ;
    @Temporal(TemporalType.DATE)
    private Date dateReception;
    private int quantiteRecevoir;
    private int quantiteAccepte;
    private int quantiteRefuse;
    private String etat;
    private boolean archive;
    @OneToOne
    private Livraison livraison;

    @JsonIgnore
    @OneToOne(mappedBy = "bonReception" , cascade = CascadeType.ALL)
    private  BonRetour bonRetour;
}
