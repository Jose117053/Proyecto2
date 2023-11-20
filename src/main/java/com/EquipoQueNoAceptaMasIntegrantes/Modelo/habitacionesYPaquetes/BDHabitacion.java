package com.EquipoQueNoAceptaMasIntegrantes.Modelo.habitacionesYPaquetes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/** Clase que modela la base de datos de las habitaciones. */
public class BDHabitacion {

    /* Los lista de habitaciones que maneja el hotel. */
    protected Habitacion[] habitaciones;

    /* La lista de días reservados. */
    private List<LocalDate> diasReservados = new ArrayList<>();

    /**
     * Constructor de la base de datos de las habitaciones, se definen 3 tipos de habitacion.
     */
    public BDHabitacion() {
        habitaciones = new Habitacion[] { new Habitacion("Normal", 100, 2, "Individual", false, false, diasReservados),
                new Habitacion("Suite", 150, 5, "King", false, true, diasReservados),
                new Habitacion("GrandSuite", 200, 8, "King", true, true, diasReservados) };
    }

    /**
     * Metodo que regresa la habitacion en la posicion recibida.
     * @param posicion el indice de la habitacion a regresar.
     * @return La habitacion en la posicion recibida.
     */
    public Habitacion getHabitacion(int posicion) {
        int i = posicion - 1;
        return habitaciones[i].clone();
    }

    /**
     * La representación en cadena de todas las habitaciones disponibles.
     * @return la cadena de todas las habitaciones disponibles.
     */
    public String habitacionesDisponibles() {
        String cadena = "** Habitaciones disponibles **" + "\n ";
        for (Habitacion habitacion : habitaciones)
            cadena += habitacion.toString() + "\n ";
        return cadena;
    }
}
