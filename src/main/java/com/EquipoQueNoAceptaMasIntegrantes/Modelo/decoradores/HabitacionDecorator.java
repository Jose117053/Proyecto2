package com.EquipoQueNoAceptaMasIntegrantes.Modelo.decoradores;

/**
 * Clase abstracta que implementa a la interfaz ExtraHabitacion, encargada de definir a los
 * métodos descripcion y costo para un objeto de tipo ExtraHabitacion.
 */
public abstract class HabitacionDecorator implements ExtraHabitacion {
    /* Un objeto de tipo ExtraHabitacion. */
    protected ExtraHabitacion habitacion;

    /**
     * Constructor de la clase.
     * @param habitacion un objeto de tipo ExtraHabitacion que será decorado.
     */
    public HabitacionDecorator(ExtraHabitacion habitacion) {
        this.habitacion = habitacion;
    }

    /**
     * Método encargado de regresar la descripción de los servicios que incluye una habitación.
     * @return una representación en cadena con la descripción de una habitación antes
     *         de que se le agregue algún decorador.
     */
    public String descripcion() {
        return habitacion.descripcion();
    }

    /**
     * Método encargado de regresar el costo de la habitación que se está reservando.
     * @return un número décimal que representa el costo de una habitación antes de que
     *         se le agregue algún decorador, es decir servicios extra.
     */
    public double costo() {
        return habitacion.costo();
    }

}
