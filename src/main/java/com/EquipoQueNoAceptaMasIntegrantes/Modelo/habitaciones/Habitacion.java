package com.EquipoQueNoAceptaMasIntegrantes.Modelo.habitaciones;

import lombok.Getter;
import lombok.Setter;
import com.EquipoQueNoAceptaMasIntegrantes.Modelo.decoradores.ExtraHabitacion;

@Getter
@Setter
public class Habitacion implements Cloneable, ExtraHabitacion {
    private String nombre;
    private int numero;
    private double costo;
    private String cama;
    private boolean vistaAlMar;
    private boolean suite;
    private int capacidad;

    // Constructor específico que ya tienes
    public Habitacion(String nombre, double costo, int capacidad, String cama, boolean vistaAlMar, boolean suite) {
        this.nombre = nombre;
        this.costo = costo;
        this.capacidad = capacidad;
        this.cama = cama;
        this.vistaAlMar = vistaAlMar;
        this.suite = suite;
    }

    public String auxSuite() {
        return suite ? "Si" : "No";
    }

    public String auxVistaAlmar() {
        return vistaAlMar ? "Si" : "No";
    }

    /**
     * Metodo que regresa la descripcion de la habitacion
     */
    public String descripcion() {
        return "Nombre: " + getNombre() + "\n " +
                "Numero de habitacion: " + getNumero() + "\n " +
                "Costo: " + getCosto() + "\n " +
                "Capacidad:" + getCapacidad() + "\n " +
                "Cama: " + getCama() + "\n " +
                "Suite: " + auxSuite() + "\n " +
                "Vista Al mar: " + auxVistaAlmar() + "\n ";
    }

    public double costo() {
        return costo;
    }

    /**
     * Metodo que clona una habitacion ya definida
     * 
     * @return
     */

    @Override
    public Habitacion clone() {
        Habitacion clon = new Habitacion(getNombre(), getCosto(), getCapacidad(), getCama(), isVistaAlMar(), isSuite());
        return clon;
    }
}
