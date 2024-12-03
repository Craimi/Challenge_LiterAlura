package com.ambystudio.LiterAlura.modelos;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "libros")
public class Libro {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;
    private Integer descargas;
    private List<String> lenguajes;

    @ManyToOne
    private Autor autor;

    public Libro() {}

    public Libro(String titulo, Integer descargas, List<String> lenguajes) {
        this.titulo = titulo;
        this.descargas = descargas;
        this.lenguajes = lenguajes;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getDescargas() {
        return descargas;
    }

    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }

    public List<String> getLenguajes() {
        return lenguajes;
    }

    public void setLenguajes(List<String> lenguajes) {
        this.lenguajes = lenguajes;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return """
                ----------Libro----------
                Titulo: %s
                Descargas: %d
                Lenguajes: %s
                -------------------------
                Autor: %s
                -------------------------""".formatted(titulo, descargas, lenguajes, autor.getNombre());
    }
}
