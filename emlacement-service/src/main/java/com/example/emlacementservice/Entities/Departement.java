package com.example.emlacementservice.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
    @Size(max = 40)
    @NotBlank
    private String nomDepartement ;
    private String fileName;
    private String fileType;

    @Lob
    private byte[] data;



    @ManyToOne
    private Emplacement departementEmplacement;

    @OneToOne
    private AdresseDepartement adresseDepartement;
}
