package com.example.commandeservice.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUser ;
    private String Fname ;
    private String Lname ;
    private String email ;
    private String Password ;


    private boolean enabled;
    @ManyToOne
    private Role role;

    @ManyToOne
    private Emplacement emplacement;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "responsableEmplacement")
    @JsonIgnore
    private Set<Emplacement> emplacementsGeres;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "acheteur")
    @JsonIgnore
    private Set<DemandeAchat> demandeAchats;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "fourniseur")
    @JsonIgnore
    private Set<DevisFourniseur> devisFourniseurs;



    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
    List<Reclamation> complaints;

}
