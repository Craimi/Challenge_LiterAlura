package com.ambystudio.LiterAlura;

import com.ambystudio.LiterAlura.conexion.ConexionPostgres;
import com.ambystudio.LiterAlura.conexion.ConexionPostgresAutores;
import com.ambystudio.LiterAlura.conexion.ConexionPostgresLibros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {

	@Autowired
	private ConexionPostgres conexion;
	@Autowired
	private ConexionPostgresLibros conexionLibros;
	@Autowired
	private ConexionPostgresAutores conexionAutores;

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(conexion, conexionLibros, conexionAutores);
		principal.menu();
	}
}
