package com.EquipoQueNoAceptaMasIntegrantes.Modelo;


public abstract class Habitacion{ //implements ExtrasHabitacion

    /* Tipo de cama de la habitacion */
    String cama;
    /* Nos dice si la habitacion cuenta con vista al mar */
    boolean vistaAlMar;
    /* Nos dice si la habitacion cuenta suite */
    boolean suite;
    /*  */
    String cenaRomantica;
    /*  */
    String flores;
    /*  */
    String champagne;
    /*  */
    String chocolates;

    /**
     * Getter que regresa el tipo de cama de la habitacion
     * @return el tipo de cama de la habitacion
     */
    public String getCama(){
        return cama;
    }

    /**
     * Getter que regresa un "Si" si la habitacion cuenta
     * con vista al mar; "No" en caso contrario
     * @return "Si" si la habitacion cuenta con vista al mar,
     * "No" si la habitacion no tiene vista al mar
     */
    public String getVistaAlmar(){
        return vistaAlMar ? "Si" : "No";
    }

    /**
     * Getter que regresa "Si" si la habitacion cuenta con
     * suite; "No" en caso contrario
     * @return "Si" si la habitacion cuenta con suite; "No"
     * si la habitacion no tiene suite
     */
    public String getSuite(){
        return suite ? "Si " : "No" ;
    }

    /**
     * Metodo que regresa la descripcion de la habitacion
     */

    public String descripcion() {
        return "Cama: " + this.getCama() + "\n " +
                "Suite: " + this.getSuite() + "\n " +
                "Vista Al mar: " + this.getVistaAlmar() + "\n ";
    }

    /*
    EN clase main:
    Habitacion habitacion = FactoryHabitaciones.creaHabitacion(1)
    System.out.println(habitacion.descripcion());
    */

}

