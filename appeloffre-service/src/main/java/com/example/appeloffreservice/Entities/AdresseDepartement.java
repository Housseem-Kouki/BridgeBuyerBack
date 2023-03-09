package com.example.appeloffreservice.Entities;

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
public class AdresseDepartement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAdresseDepartement ;

    private  String emplacementDepartement ;

    private String codePostal ;


    @JsonIgnore
    @OneToOne(mappedBy = "adresseDepartement" , cascade = CascadeType.ALL)
    private Departement departement;

}
