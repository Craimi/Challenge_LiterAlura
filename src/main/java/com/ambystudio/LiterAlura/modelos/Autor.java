package com.ambystudio.LiterAlura.modelos;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nombre;
    private Integer añoNacimiento;
    private Integer añoFallecimiento;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
    private List<Libro> libro;

    public Autor() {}

    public Autor(String nombre, Integer añoNacimiento, Integer añoFallecimiento) {
        this.nombre = nombre;
        this.añoNacimiento = añoNacimiento;
        this.añoFallecimiento = añoFallecimiento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAñoNacimiento() {
        return añoNacimiento;
    }

    public void setAñoNacimiento(Integer añoNacimiento) {
        this.añoNacimiento = añoNacimiento;
    }

    public Integer getAñoFallecimiento() {
        return añoFallecimiento;
    }

    public void setAñoFallecimiento(Integer añoFallecimiento) {
        this.añoFallecimiento = añoFallecimiento;
    }

    public List<Libro> getLibro() {
        return libro;
    }

    public void setLibro(List<Libro> libro) {
        this.libro = libro;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "nombre='" + nombre + '\'' +
                ", añoNacimiento=" + añoNacimiento +
                ", añoFallecimiento=" + añoFallecimiento +
                '}';
    }
}
