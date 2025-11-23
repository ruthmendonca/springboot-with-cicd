package com.example.mpb.model;

public class Musica {
    private Long id;
    private String titulo;
    private String artista;
    private Integer ano;

    public Musica() {}

    public Musica(Long id, String titulo, String artista, Integer ano) {
        this.id = id;
        this.titulo = titulo;
        this.artista = artista;
        this.ano = ano;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }
}
