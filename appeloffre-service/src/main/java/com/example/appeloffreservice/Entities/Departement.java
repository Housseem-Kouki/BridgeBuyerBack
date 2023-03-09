package com.example.appeloffreservice.Entities;

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
public class Departement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDepartement ;
    private String nomDepartement ;



    @ManyToOne
    private Emplacement departementEmplacement;

    @OneToOne
    private AdresseDepartement adresseDepartement;
}
