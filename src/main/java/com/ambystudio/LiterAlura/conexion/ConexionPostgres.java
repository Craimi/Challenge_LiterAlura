package com.ambystudio.LiterAlura.conexion;

import com.ambystudio.LiterAlura.modelos.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConexionPostgres extends JpaRepository<Autor, Long> {
}
