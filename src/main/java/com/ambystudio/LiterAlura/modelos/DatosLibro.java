package com.ambystudio.LiterAlura.modelos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<DatosAutores> autores,
        @JsonAlias("download_count") Integer descargas,
        @JsonAlias("languages") List<String> lenguajes
) {
}
