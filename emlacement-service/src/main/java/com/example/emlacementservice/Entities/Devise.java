package com.example.emlacementservice.Entities;

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
public class Devise implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDevise ;
    private String nomDevise ;
    private Float tauxInversion ;
    private Boolean etat ;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "devise")
    @JsonIgnore
    private Set<Emplacement> emplacements;

}
