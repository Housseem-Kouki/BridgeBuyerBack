package com.example.userservice.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Emplacement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEmplacement ;

    private String nomEmplacement ;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "emplacement")
    @JsonIgnore
    private Set<User> users;

    @ManyToOne
    @JsonIgnore
    private User responsableEmplacement;

    @ManyToOne
    private Devise devise;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "departementEmplacement")
    @JsonIgnore
    private Set<Departement> departements;

    @OneToOne
    private AdresseExpedition adresseExpedition;

}
