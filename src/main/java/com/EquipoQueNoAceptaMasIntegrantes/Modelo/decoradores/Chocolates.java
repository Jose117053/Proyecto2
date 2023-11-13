package com.EquipoQueNoAceptaMasIntegrantes.Modelo.decoradores;

import java.io.IOException;

import com.EquipoQueNoAceptaMasIntegrantes.Controladores.util.Mensajes;

/**
 * Clase Chocolates que hereda de la clase HabitacionDecorator, encargada de
 * definir un servicio
 * extra que se ofrece al hospedarse en el hotel, definira lo que incluye así
 * como su costo.
 */
public class Chocolates extends HabitacionDecorator {

    /**
     * Constructor de la clase.
     * 
     * @param habitacion
     */
    public Chocolates(ExtraHabitacion habitacion) {
        super(habitacion);
    }

    /**
     * Método encargado de regresar la descripción de los servicios que incluye una
     * habitación
     * más un servicio extra.
     * 
     * @returns una representación en cadena con la descripción de todos los
     *          servicios incluídos
     *          en la habitación que se está reservando.
     */
    @Override
    public String descripcion(String codigoPais) throws IOException {
        String chocolates = Mensajes.cargarMensajes(codigoPais).getProperty("msg.chocolates");
        return habitacion.descripcion(codigoPais) + chocolates;
    }

    /**
     * Método encargado de definir el costo del servicio extra que se esta
     * solicitando.
     * 
     * @return un número que representa el costo del servicio extra en dólares.
     */
    @Override
    public double costo() {
        return 10;
    }
}
