package com.example.mpb.service;

import com.example.mpb.model.Musica;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class MusicaService {
    private final List<Musica> armazen = new ArrayList<>();
    private final AtomicLong seq = new AtomicLong(1);

    public MusicaService() {
        // Seed com 3 músicas de MPB
        armazen.add(new Musica(seq.getAndIncrement(), "Aguas de Março", "Tom Jobim", 1972));
        armazen.add(new Musica(seq.getAndIncrement(), "Tocando em Frente", "Almir Sater", 1990));
        armazen.add(new Musica(seq.getAndIncrement(), "Cálice", "Chico Buarque", 1978));
    }

    public List<Musica> listar() {
        return new ArrayList<>(armazen);
    }

    public Optional<Musica> porId(Long id) {
        return armazen.stream().filter(m -> m.getId().equals(id)).findFirst();
    }

    public List<Musica> porArtista(String artista) {
        return armazen.stream()
                .filter(m -> m.getArtista().equalsIgnoreCase(artista))
                .collect(Collectors.toList());
    }

    public Musica adicionar(Musica m) {
        m.setId(seq.getAndIncrement());
        armazen.add(m);
        return m;
    }
}
