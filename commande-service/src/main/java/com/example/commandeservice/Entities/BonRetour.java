package com.example.commandeservice.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class BonRetour implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBonRetour ;
    private int quantiteRefusee;
    private String commmentaire;
    private boolean archive;
    @OneToOne
    private BonReception bonReception;

    @JsonIgnore
    @OneToOne(mappedBy = "bonRetour" , cascade = CascadeType.ALL)
    private FactureAvoir factureAvoir;

}
