package com.ambystudio.LiterAlura.conexion;

import com.ambystudio.LiterAlura.modelos.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConexionPostgresAutores extends JpaRepository<Autor, Long> {
    @Query(value = "SELECT * FROM autores", nativeQuery = true)
    List<Autor> findAutores();
    @Query(value = "SELECT * FROM autores WHERE año_fallecimiento > :fallecimiento AND año_nacimiento < :fallecimiento", nativeQuery = true)
    List<Autor> findAutoresByAño(@Param("fallecimiento") Integer añoFallecimiento);
}
