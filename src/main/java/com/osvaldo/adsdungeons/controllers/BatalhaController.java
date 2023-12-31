package com.osvaldo.adsdungeons.controllers;

import com.osvaldo.adsdungeons.domain.*;
import com.osvaldo.adsdungeons.dtos.BatalhaDTO;
import com.osvaldo.adsdungeons.dtos.PersonagemDTO;
import com.osvaldo.adsdungeons.dtos.UsuarioDTO;
import com.osvaldo.adsdungeons.repositories.BatalhaRepository;
import com.osvaldo.adsdungeons.repositories.InimigosRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class BatalhaController {

    @Autowired
    BatalhaRepository batalhaRepository;

    @Autowired
    InimigosRepository inimigoRepository;

    @GetMapping("/batalhas")
    public ResponseEntity<List<Batalha>> getBatalhas(){
        List<Batalha> batalhas = batalhaRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(batalhas);
    }

    @PostMapping("/batalha")
    public ResponseEntity<Batalha> saveBatalhas(@RequestBody @Valid BatalhaDTO batalhaDTO){
        var batalha = new Batalha();
        batalha.setInimigos(new ArrayList<>());
        for (UUID inimigoID : batalhaDTO.inimigos()){
            var inimigo = inimigoRepository.findById(inimigoID);
            batalha.getInimigos().add(inimigo.get());
        }
        batalhaRepository.save(batalha);
        return ResponseEntity.status(HttpStatus.OK).body(batalha);
    }

    @GetMapping("/batalha/{id}")
    public ResponseEntity<Batalha> getOneBatalha(@PathVariable(value = "id") UUID id) {
        Optional<Batalha> batalha = batalhaRepository.findById(id);
        return batalha.map(value -> ResponseEntity.status(HttpStatus.OK).body(value)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @GetMapping("/batalha-random")
    public ResponseEntity<Batalha> getOneRandomBatalha() {
        List<Batalha> batalha = batalhaRepository.findAll();
        if(batalha.toArray().length == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        int random_int = (int) Math.floor(Math.random() * (batalha.toArray().length));
        return ResponseEntity.status(HttpStatus.OK).body(batalha.get(random_int));
    }


}
