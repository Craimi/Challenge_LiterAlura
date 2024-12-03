package com.ambystudio.LiterAlura;

import com.ambystudio.LiterAlura.conexion.ConexionPostgres;
import com.ambystudio.LiterAlura.conexion.ConexionPostgresAutores;
import com.ambystudio.LiterAlura.conexion.ConexionPostgresLibros;
import com.ambystudio.LiterAlura.modelos.*;
import com.ambystudio.LiterAlura.services.ConsumoAPI;
import com.ambystudio.LiterAlura.services.ConvertirDatos;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.*;

public class Principal {
    private final ConexionPostgres conexion;
    private final ConexionPostgresLibros conexionLibros;
    private final ConexionPostgresAutores conexionAutores;

    private final Scanner input = new Scanner(System.in);
    private final ConsumoAPI consumoAPI = new ConsumoAPI();
    private final ConvertirDatos convertirDatos = new ConvertirDatos();
    private final String URL_BASE = "https://gutendex.com/books/?search=";

    public Principal(ConexionPostgres conexion, ConexionPostgresLibros conexionLibros, ConexionPostgresAutores conexionAutores) {
        this.conexion = conexion;
        this.conexionLibros = conexionLibros;
        this.conexionAutores = conexionAutores;
    }

    public void menu(){
        var opcion = -1;
        while (opcion != 0){
            var menu = """
                        *****************************************
                        *          L I T E R A L U R A          *
                        *****************************************
                        * 1 - Buscar libro por titulo           *
                        * 2 - Listar libros registrados         *
                        * 3 - Listar autores registrados        *
                        * 4 - Listar autores por fechas         *
                        * 5 - Listar libros por idioma          *
                        * 0 - Salir                             *
                        *****************************************""";
            System.out.println(menu);

            while (true) {
                System.out.print("Ingrese una opción: ");

                try {
                    opcion = Integer.parseInt(input.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Por favor, ingresa un número válido.");
                }
            }

            switch (opcion){
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresSegunFecha();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
                default:
                    System.out.println("Caso no valido");
                    break;
            }
        }
    }

    public void buscarLibro(){
        System.out.println("¿Que libro desea consultar? (Si no recuerda el nombre completo puede usar" +
                " un fragmento del nombre)");

        var nombre = input.nextLine();

        var json = consumoAPI.obtenerDatos(URL_BASE + nombre.replace(" ", "+"));

        var datosEspecificos = convertirDatos.obtenerDatos(json, DatosGenerales.class);

        Optional<DatosLibro> libroEncontrado = datosEspecificos.resultados().stream()
                .filter(e -> e.titulo().toUpperCase().contains(nombre.toUpperCase()))
                .findFirst();

        if(libroEncontrado.isPresent()){
            Optional<DatosAutores> autorEncontrado = libroEncontrado.get().autores().stream().findFirst();

            try {
                registrarAutor(autorEncontrado);
            } catch (DataIntegrityViolationException e){
                System.out.println();
            }

            List<Libro> listaLibro = new ArrayList<>();

            Libro registroNuevoLibro = new Libro(
                    libroEncontrado.get().titulo(),
                    libroEncontrado.get().descargas(),
                    libroEncontrado.get().lenguajes()
            );

            Optional<Autor> autor = conexion.findAll().stream()
                    .filter(s -> s.getNombre().toLowerCase().contains(autorEncontrado.get().nombre().toLowerCase()))
                    .findFirst();

            registroNuevoLibro.setAutor(autor.get());

            listaLibro.add(registroNuevoLibro);

            autor.get().setLibro(listaLibro);

            try {
                conexion.save(autor.get());
                System.out.println(registroNuevoLibro);
                System.out.println("Libro Registrado");
            }catch (DataIntegrityViolationException e){
                System.out.println("El libro ya existe en la base de datos");
            }

        } else {
            System.out.println("No encontrado");
        }
    }

    public void registrarAutor(Optional<DatosAutores> autorEncontrado){
        Autor registroNuevoAutor = new Autor(
                autorEncontrado.get().nombre(),
                autorEncontrado.get().añoNacimiento(),
                autorEncontrado.get().añoFallecimiento()
        );
        conexion.save(registroNuevoAutor);
    }

    public void listarLibrosRegistrados(){
        List<Libro> listados =  conexionLibros.findLibros();
        listados.forEach(e->
                System.out.printf("* '%s' by %s, [%d downloads] - Language: %s%n",
                        e.getTitulo(),
                        e.getAutor().getNombre(),
                        e.getDescargas(),
                        e.getLenguajes()));
    }

    public void listarAutoresRegistrados(){
        List<Autor> listados = conexionAutores.findAutores();
        listados.forEach(e->
                System.out.printf("* %s, [%d] - [%d]%n",
                        e.getNombre(),
                        e.getAñoNacimiento(),
                        e.getAñoFallecimiento()));
    }

    public void listarAutoresSegunFecha(){
        System.out.println("Ingrese el año que desea consultar: ");
        Integer fecha = Integer.parseInt(input.nextLine());

        List<Autor> listados = conexionAutores.findAutoresByAño(fecha);
        listados.forEach(e->
                System.out.printf("* %s, [%d] - [%d]%n",
                        e.getNombre(),
                        e.getAñoNacimiento(),
                        e.getAñoFallecimiento()));
    }

    public void listarLibrosPorIdioma(){
        System.out.println("--- Idiomas registrados ---");
        List<String[]> idiomasListados = conexionLibros.findIdiomas();
        idiomasListados.forEach(e ->
                System.out.println(Arrays.toString(e)));

        System.out.println("¿En que idioma busca los libros? ");
        var idioma = input.nextLine();

        List<Libro> listados =  conexionLibros.findLibrosByIdioma(idioma.toLowerCase());
        if(listados.isEmpty()){
            System.out.println("Opcion no valida");
        }
        else {
            System.out.println(listados);
        }
    }
}
