package com.osvaldo.adsdungeons.controllers;

import com.osvaldo.adsdungeons.domain.*;
import com.osvaldo.adsdungeons.dtos.BasicPersonagemDTO;
import com.osvaldo.adsdungeons.dtos.HPDTO;
import com.osvaldo.adsdungeons.dtos.PersonagemDTO;
import com.osvaldo.adsdungeons.dtos.UsuarioDTO;
import com.osvaldo.adsdungeons.repositories.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/usuario")
    public ResponseEntity<Usuario> saveUsuario(@RequestBody @Valid UsuarioDTO usuarioDTO){
        var usuario = new Usuario();
        List<Personagem> list = new ArrayList<>();
        for(var item : usuarioDTO.personagens()){
            switch (item.type()){
                case 'G':
                    var guerreiro = new Guerreiro();
                    guerreiro.setNome(item.nome());
                    list.add(guerreiro);
                    break;
                case 'S':
                    var sacerdote = new Sacerdote();
                    sacerdote.setNome(item.nome());
                    list.add(sacerdote);
                    break;
                case 'A':
                    var atirador = new Atirador();
                    atirador.setNome(item.nome());
                    list.add(atirador);
                    break;
                case 'M':
                    var mago = new Mago();
                    mago.setNome(item.nome());
                    list.add(mago);
                    break;
            }
        }
        usuario.setNome(usuarioDTO.nome());
        usuario.setPersonagens(list);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(usuario));
    }

    @PatchMapping("/usuario/{id}")
    public ResponseEntity<Usuario> addCharacter(@RequestBody @Valid BasicPersonagemDTO basicPersonagemDTO, @PathVariable(value = "id") UUID id){
        Optional<Usuario> usuarioO = usuarioRepository.findById(id);
        if (usuarioO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        var usuario = usuarioO.get();
        List<Personagem> list = usuario.getPersonagens();
        Personagem personagem = null;
        switch (basicPersonagemDTO.type()){
            case 'G':
                personagem = new Guerreiro();
                break;
            case 'S':
                personagem = new Sacerdote();
                break;
            case 'A':
                personagem = new Atirador();
                break;
            case 'M':
                personagem = new Mago();
                break;
        }
        personagem.setNome(basicPersonagemDTO.nome());
        list.add(personagem);

        usuario.setPersonagens(list);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(usuario));

    }

}
