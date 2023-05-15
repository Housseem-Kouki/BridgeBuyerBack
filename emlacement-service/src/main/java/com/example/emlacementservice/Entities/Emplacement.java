package com.example.emlacementservice.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Emplacement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEmplacement ;
    @NotBlank
    @Size(max = 40)
    private String nomEmplacement ;

    @JsonIgnore
    @OneToMany(mappedBy = "emplacement" , cascade = CascadeType.ALL)
    private List<User> users;

    @JsonIgnore
    @ManyToOne
    private User responsableEmplacement;


    private String devise;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "departementEmplacement")
    @JsonIgnore
    private List<Departement> departements;

    @JsonIgnore
    @OneToOne
    private AdresseExpedition adresseExpedition;

}
