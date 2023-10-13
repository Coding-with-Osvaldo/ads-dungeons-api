package com.osvaldo.adsdungeons.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Inimigo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    private Integer dano;
    private Float vida;

    @ElementCollection
    private List<String> habilidades = new ArrayList<>();

    @ManyToMany(mappedBy = "inimigos")
    List<Personagem> personagems = new ArrayList<>();


}
