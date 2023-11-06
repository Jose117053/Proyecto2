package com.EquipoQueNoAceptaMasIntegrantes.Modelo;

public class HabitacionNormal extends Habitacion {

    /**
     * Constructor de la habitacion normal, solo
     * tiene una cama individual y no tiene suite
     * ni vista al mar.
     */
    public HabitacionNormal(){
        cama="Individual";
        suite=false;
        vistaAlMar=false;
    }

}
