package com.EquipoQueNoAceptaMasIntegrantes.Modelo.paquetes;

/**
 * Clase que simula la fabrica de paquetes
 */

public class FactoryPaquetes {
    /**
     * Metodo estatico para la creacion de paquetes
     * @param tipo el tipo de paquete a crear
     * @return el paquete del tipo recibido
     */
    public static Paquete creaPaquete(int tipo){//switch sobre paquetes, 1:aventura 2:cultural 3:relax
        switch (tipo){
            case 1:
                return new PaqueteAventuraCancun();
            case 2:
                return new PaqueteCulturalCancun();
            case 3:
                return new PaqueteRelaxCancun();
            default:
                System.out.println("Ingresa un numero valido");
                System.exit(1); //falta hacer un bucle
        }
        return null;
    }
}
