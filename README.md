# Challenge LiterAlura

Reto de programación enfocado en la practica con Java + Spring para Oracle Next Education.

# Reto

Generar un programa que consulte, gestione y almacene informacion obtenida de la API de [Gutendex](https://gutendex.com) de diversos libros y autores, permitiendo al usuario consultar y listar la informacion previamente almacenada.

# Tecnologias empleadas

* Backend: Java
* Gestion y manipulacion de datos: Spring Data / Jackson
* Base de datos: PostgreSQL

# Opciones disponibles
* Busqueda de libro por titulo (Almacena el resultado en la base de datos).
* Listar libros registrados.
* Listar autores registrados.
* Buscar autores por fecha.
* Listar libros por idioma.

# Como configurar el proyecto
1. Obtener una copia de este programa.
   * Clonando el repositorio.
   * Descargando manualmente el repositorio.
2. Crear una base de datos local dedicada al proyecto.
3. Modificar el archivo `application.properties` con las variables locales de tu sistema
  * Si usas PostgreSQL, va de la siguiente forma:
  ```
  spring.datasource.url=jdbc:postgresql://localhost:5432/${NOMBRE_BASE_DATOS}
  spring.datasource.username=${POSTGRESQL_USERNAME}
  spring.datasource.password=${POSTGRESQL_PASSWORD}
  spring.datasource.driver-class-name=org.postgresql.Driver
  ```
  * Si usas MySQL, va de la siguiente forma:
    
  ```
  spring.datasource.url=jdbc:mysql://localhost:5432/${NOMBRE_BASE_DATOS}
  spring.datasource.username=${MYSQL_USERNAME}
  spring.datasource.password=${MYSQL_PASSWORD}
  spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
  ```
4. Ejecutar desde IntelliJ
