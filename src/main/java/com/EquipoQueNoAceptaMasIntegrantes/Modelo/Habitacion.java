package com.EquipoQueNoAceptaMasIntegrantes.Modelo;
public class Habitacion implements Cloneable{ //implements ExtrasHabitacion
    /* Tipo de habitacion */
    String nombre;
    /* El numero de la habitacion */
    int numero;
    /* El costo de la habitacion */
    double costo;
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

    public Habitacion(String nombre,double costo,String cama, boolean vistaAlMar, boolean suite){
        this.nombre =nombre;
        this.costo=costo;
        this.cama=cama;
        this.vistaAlMar=vistaAlMar;
        this.suite=suite;
    }

    /**
     * Getter que regresa el nombre de la habitacion
     * @return El nombre de la habitacion
     */
    public String getNombre(){
        return nombre;
    }

    /**
     * Getter que regresa el numero de habitacion
     * @return El numero de habitacion
     */
    public int getNumero(){
        return numero;
    }
    /**
     * Setter que define el numero de habitacion
     */
    public void setNumero(int numero){
        this.numero=numero;
    }

    /**
     * Getter que regresa el costo de la habitacion
     * @return El costo de la habitacion
     */
    public double getCosto(){
        return costo;
    }
    /**
     * Getter que regresa el tipo de cama de la habitacion
     * @return el tipo de cama de la habitacion
     */
    public String getCama(){
        return cama;
    }

    /**
     * Getter que regresa true si la habitacion cuenta
     * con vista al mar, false en caso contrario
     * @return True si la habitacion cuenta con vista al
     * mar, false en caso contrario
     */
    public boolean getVistaAlmar(){
        return vistaAlMar;
    }

    /**
     * Getter que regresa true si la habitacion cuenta
     * con suite, false en caso contrario
     * @return True si la habitacion cuenta con suite,
     * false en caso contrario
     */
    public boolean getSuite(){
        return suite;
    }

    /*Metodos auxiliares para regresar un string dependiendo del valor booleano*///necesarios?

    /**
     * Getter que regresa "Si" si la habitacion cuenta con
     * suite; "No" en caso contrario
     * @return "Si" si la habitacion cuenta con suite; "No"
     * si la habitacion no tiene suite
     */

    public String auxSuite(){
        return suite ? "Si " : "No" ;
    }

    /**
     * Getter que regresa un "Si" si la habitacion cuenta
     * con vista al mar; "No" en caso contrario
     * @return "Si" si la habitacion cuenta con vista al mar,
     * "No" si la habitacion no tiene vista al mar
     */

    public String auxVistaAlmar(){
        return vistaAlMar ? "Si" : "No";
    }

    /**
     * Metodo que regresa la descripcion de la habitacion
     */
    public String descripcion() {
        return  "Nombre: " + getNombre() + "\n " +
                "Numero de habitacion: " + getNumero() + "\n " +
                "Costo: " + getCosto() + "\n " +
                "Cama: " + getCama() + "\n " +
                "Suite: " + auxSuite() + "\n " +
                "Vista Al mar: " + auxVistaAlmar() + "\n ";
    }

    /**
     * Metodo que clona una habitacion ya definida
     * @return
     */

    @Override
    public Habitacion clone() {
        Habitacion clon= new Habitacion(getNombre(), getCosto(), getCama(), getVistaAlmar(), getSuite());
        return clon;
    }
}

