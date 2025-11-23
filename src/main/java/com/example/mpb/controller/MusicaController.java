package com.example.mpb.controller;

import com.example.mpb.model.Musica;
import com.example.mpb.service.MusicaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/musicas")
public class MusicaController {

    private final MusicaService service;

    public MusicaController(MusicaService service) {
        this.service = service;
    }

    // Rota 1: listar todas
    @GetMapping
    public List<Musica> listar() {
        return service.listar();
    }

    // Rota 2: buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<Musica> porId(@PathVariable Long id) {
        return service.porId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Rota 3: buscar por artista
    @GetMapping("/artista/{artista}")
    public List<Musica> porArtista(@PathVariable String artista) {
        return service.porArtista(artista);
    }

    // Rota adicional: criar nova música
    @PostMapping
    public ResponseEntity<Musica> criar(@RequestBody Musica musica) {
        Musica salvo = service.adicionar(musica);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }
}
