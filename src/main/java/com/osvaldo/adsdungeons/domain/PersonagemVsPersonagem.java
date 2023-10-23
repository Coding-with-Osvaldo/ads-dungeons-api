package com.osvaldo.adsdungeons.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonagemVsPersonagem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID ID;

    @ManyToOne
    Personagem personagem1;

    @ManyToOne
    Personagem personagem2;
}