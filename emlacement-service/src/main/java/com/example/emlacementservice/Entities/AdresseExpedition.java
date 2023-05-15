package com.example.emlacementservice.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdresseExpedition implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAdresseExpedition ;
    @NotBlank
    private String cite ;
    @NotBlank
    private String pays ;
    @NotBlank
    private String codePostal ;

    @JsonIgnore
    @OneToOne(mappedBy = "adresseExpedition" , cascade = CascadeType.ALL)
    private  Emplacement emplacement;

}
