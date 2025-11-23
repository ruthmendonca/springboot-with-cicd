package com.example.mpb.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MusicaControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void listarTodas() throws Exception {
        mvc.perform(get("/api/musicas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    void buscarPorId() throws Exception {
        mvc.perform(get("/api/musicas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo", is("Aguas de Março")));
    }

    @Test
    void buscarPorArtista() throws Exception {
        mvc.perform(get("/api/musicas/artista/Chico Buarque"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].titulo", is("Cálice")));
    }

    @Test
    void criarMusica() throws Exception {
        String nova = "{ \"titulo\": \"Olha\", \"artista\": \"Caetano Veloso\", \"ano\": 1993 }";
        mvc.perform(post("/api/musicas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(nova))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.titulo", is("Olha")));
    }
}
