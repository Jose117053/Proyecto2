package com.EquipoQueNoAceptaMasIntegrantes.Modelo.habitacionesYPaquetes;

import lombok.Getter;
import lombok.Setter;
import com.EquipoQueNoAceptaMasIntegrantes.Modelo.decoradores.ExtraHabitacion;

@Getter
@Setter
/** Clase que modela las habitaciones del hotel. */
public class Habitacion implements Cloneable, ExtraHabitacion {

    /* El nombre del tipo de habitación. */
    private String nombre;
    /* El número de habitación. */
    private int numero;
    /* El costo de la habitación. */
    private double costo;
    /* El tipo de cama de la habitación. */
    private String cama;
    /* El tipo de habitación GrandSuite. */
    private boolean vistaAlMar;
    /* El tipo de habitación Suite. */
    private boolean suite;
    /* La capacidad máxima de personas de la habitación. */
    private int capacidad;

    /**
     * Constructor de habitaciones.
     * @param nombre El nombre del tipo de habitación.
     * @param costo El costo de la habitación.
     * @param capacidad La capacidad máxima de personas de la habitación.
     * @param cama El tipo de cama de la habitación.
     * @param vistaAlMar El tipo de habitación GrandSuite.
     * @param suite El tipo de habitación Suite.
     */
    public Habitacion(String nombre, double costo, int capacidad, String cama, boolean vistaAlMar, boolean suite) {
        this.nombre = nombre;
        this.costo = costo;
        this.capacidad = capacidad;
        this.cama = cama;
        this.vistaAlMar = vistaAlMar;
        this.suite = suite;
    }

    /**
     * Método auxiliar que devuelve una cadena dependiendo del atributo suite.
     * @return la cadena que indica si una habitación es de tipo Suite.
     */
    public String auxSuite() {
        return suite ? "Si" : "No";
    }

    /**
     * Método auxiliar que devuelve una cadena dependiendo del atributo vistaAlMar.
     * @return la cadena que indica si una habitación es de tipo GrandSuite.
     */
    public String auxVistaAlmar() {
        return vistaAlMar ? "Si" : "No";
    }

    /**
     * Metodo que regresa la descripcion de la habitacion en forma de cadena.
     * @return la cadena de representación de la habitación.
     */
    @Override
    public String toString() {
        return "Nombre: " + getNombre() + "\n " +
                "Numero de habitacion: " + getNumero() + "\n " +
                "Costo: " + getCosto() + "\n " +
                "Capacidad:" + getCapacidad() + "\n " +
                "Cama: " + getCama() + "\n " +
                "Suite: " + auxSuite() + "\n " +
                "Vista Al mar: " + auxVistaAlmar() + "\n ";
    }

    // Tener cuidado con el costo de habitación vs costo extra.
    public double costo() {
        return costo;
    }

    /**
     * Metodo que clona una habitacion ya definida.
     * @return la habitación clonada.
     */
    @Override
    public Habitacion clone() {
        Habitacion clon = new Habitacion(getNombre(), getCosto(), getCapacidad(), getCama(), isVistaAlMar(), isSuite());
        return clon;
    }

    /**
     * 
     * @param codigoPais
     * @returns
     */
    @Override
    public String descripcion(String codigoPais) {
        return "Nombre: " + getNombre() + "\n " +
               "Numero de habitacion: " + getNumero() + "\n " +
               "Costo: " + getCosto() + "\n " +
               "Capacidad:" + getCapacidad() + "\n " +
               "Cama: " + getCama() + "\n " +
               "Suite: " + auxSuite() + "\n " +
               "Vista Al mar: " + auxVistaAlmar() + "\n ";
    }
}
