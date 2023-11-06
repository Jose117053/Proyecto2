package com.EquipoQueNoAceptaMasIntegrantes.Modelo;

/** Clase que modela la fabrica de habitaciones*/

public class FactoryHabitaciones {

    /**
     * Metodo que servira para fabricar las habitaciones
     * de nuestro hotel dependiendo del tipo que eligio
     * el cliente
     * @param tipo el tipo de habitacion a fabricar
     * @return la habitacion del tipo recibido
     */

    public static Habitacion creaHabitacion(int tipo){//switch sobre habitacciones, 1:NOrmal 2:suite 3:suitevistaalmar
        switch (tipo){
            case 1:
                return new HabitacionNormal();
            case 2:
                return new HabitacionSuite();
            case 3:
                return new HabitacionSuiteVistaAlMar();
            default:
                System.out.println("Ingresa un numero valido");
                System.exit(1); //falta hacer un bucle en main
        }
        return null;
    }


}

