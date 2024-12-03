package com.ambystudio.LiterAlura.services;

public interface IConvertirDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
