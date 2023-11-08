package com.EquipoQueNoAceptaMasIntegrantes.Modelo.habitaciones;

/**
 * Clase que modela la base de datos de las habitaciones
 */

public class BDHabitacion {
    /* Los tipos de habitaciones que maneja nuestro hotel */
    protected Habitacion[] habitaciones;

    /**
     * Constructor de nuestra base de datos de las
     * habitaciones, se definen 3 tipos de habitacion.
     */
    public BDHabitacion() {
        habitaciones = new Habitacion[] { new Habitacion("Normal", 100, 2, "Individual", false, false),
                new Habitacion("Suite", 150, 5, "King", false, true),
                new Habitacion("GrandSuite", 200, 8, "King", true, true) };
    }

    /**
     * Metodo que regresa la habitacion en la posicion
     * recibida
     * 
     * @param posicion el indice de la habitacion a regresar
     * @return La habitacion en la posicion recibida
     */
    public Habitacion getHabitacion(int posicion) {
        int i = posicion - 1;
        return habitaciones[i].clone();
    }

    // metodo necesario??
    public String habitacionesDisponibles() {
        String cadena = "** Habitaciones disponibles **" + "\n ";
        for (Habitacion habitacion : habitaciones)
            cadena += habitacion.descripcion() + "\n ";
        return cadena;
    }
}
