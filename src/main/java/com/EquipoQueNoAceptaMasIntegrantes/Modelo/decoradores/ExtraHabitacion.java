package com.EquipoQueNoAceptaMasIntegrantes.Modelo.decoradores;

/**
 * Interfaz encargada de definir los métodos para obtener la descripción y costo de los diferentes
 * servicios que incluye o se le pueden agregar a una habitación al momento de reservarla.
 */
public interface ExtraHabitacion {
    
    /**
     * Método encargado de regresar la descripción de los servicios que incluye una habitación
     * más un servicio extra.
     * @returns una representación en cadena con la descripción de todos los servicios incluídos
     *          en la habitación que se está reservando.
     */
    public String descripcion();

    /**
     * Método encargado de definir el costo del servicio extra que se esta solicitando.
     * @return un número que representa el costo del servicio extra en dólares.
     */
    public double costo();
}
