package com.example.userservice.Entities;

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
public class Livraison implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLivraison ;
    private int quantiteDelivre;
    // private int quantiteRetour;
    @Temporal(TemporalType.DATE)
    private Date dateLivraison;
    private String etat;
    private boolean archive;

    @ManyToOne
    @JsonIgnore
    private Commande commande;

    @Override
    public boolean equals(Object o1) {
        if (this == o1) return true;
        if (!(o1 instanceof Livraison)) return false;
        Livraison livraison = (Livraison) o1;
        return  quantiteDelivre == livraison.quantiteDelivre ;
    }

    @Override
    public String toString() {
        return "Livraison{" +
                "idLivraison=" + idLivraison +
                ", quantiteDelivre=" + quantiteDelivre +
                ", dateLivraison=" + dateLivraison +
                ", archive=" + archive +
                ", commande=" + commande +
                ", bonReception=" + bonReception +
                '}';
    }

    @JsonIgnore
    @OneToOne(mappedBy = "livraison" , cascade = CascadeType.ALL)
    private BonReception bonReception;
}
