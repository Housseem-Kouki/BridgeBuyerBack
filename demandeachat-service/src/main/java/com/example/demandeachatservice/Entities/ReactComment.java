package com.example.demandeachatservice.Entities;

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
//@Table(uniqueConstraints={@UniqueConstraint(columnNames={"user_id", "comment_id"})})
public class ReactComment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idReact ;

    @Enumerated(value = EnumType.STRING)
    private React react ;

    @ManyToOne
    @JsonIgnore
    private Comment comments ;
    @OneToOne
    @JsonIgnore
    private User user ;
}
