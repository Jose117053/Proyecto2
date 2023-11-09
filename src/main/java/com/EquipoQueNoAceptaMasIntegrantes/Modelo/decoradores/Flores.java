package com.EquipoQueNoAceptaMasIntegrantes.Modelo.decoradores;

/**
 * Clase Flores que hereda de la clase HabitacionDecorator, encargada de definir un servicio
 * extra que se ofrece al hospedarse en el hotel, definira lo que incluye así como su costo.
 */
public class Flores extends HabitacionDecorator {
    /**
     * Constructor de la clase.
     * @param habitacion
     */
    public Flores(ExtraHabitacion habitacion) {
        super(habitacion);
    }

    /**
     * Método encargado de regresar la descripción de los servicios que incluye una habitación
     * más un servicio extra.
     * @returns una representación en cadena con la descripción de todos los servicios incluídos
     *          en la habitación que se está reservando.
     */
    @Override public String descripcion() {
        return habitacion.descripcion() + "\nBouquet de flores.";
    }

    /**
     * Método encargado de definir el costo del servicio extra que se esta solicitando.
     * @return un número que representa el costo del servicio extra en dólares.
     */
    @Override public double costo() {
        return 200;
    }
}