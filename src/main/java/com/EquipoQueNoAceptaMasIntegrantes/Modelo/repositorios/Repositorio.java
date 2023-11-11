package com.EquipoQueNoAceptaMasIntegrantes.Modelo.repositorios;

import java.util.Collection;

/** Define un inventario con operaciones básicas para buscar elementos. */
public interface Repositorio<T> {

    /**
     * Busca un elemento en el inventario usando su clave primaria.
     * @param PK Clave primaria del elemento a buscar.
     * @return El elemento encontrado, o null si no se encuentra.
     */
    public T find(Long PK);

    /**
     * Obtiene todos los elementos del inventario.
     * @return Una colección con todos los elementos.
     */
    public Collection<T> findAll();
}
