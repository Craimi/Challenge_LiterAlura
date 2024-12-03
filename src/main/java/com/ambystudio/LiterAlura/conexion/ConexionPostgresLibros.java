package com.ambystudio.LiterAlura.conexion;

import com.ambystudio.LiterAlura.modelos.Libro;
import org.hibernate.mapping.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConexionPostgresLibros extends JpaRepository<Libro, Long> {
    @Query(value = "SELECT * FROM libros", nativeQuery = true)
    List<Libro> findLibros();

    @Query(value = "SELECT * FROM libros WHERE :idioma = ANY(lenguajes)", nativeQuery = true)
    List<Libro> findLibrosByIdioma(@Param("idioma") String idioma);

    @Query(value = "SELECT lenguajes from libros GROUP BY lenguajes", nativeQuery = true)
    List<String[]> findIdiomas();
}
