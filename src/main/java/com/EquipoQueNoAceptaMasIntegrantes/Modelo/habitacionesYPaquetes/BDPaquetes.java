package com.EquipoQueNoAceptaMasIntegrantes.Modelo.habitacionesYPaquetes;

public class BDPaquetes {
    /* Los lista de habitaciones que maneja el hotel. */
    protected Paquete[] paquetes;

    /**
     * Constructor de la base de datos de las habitaciones, se definen 3 tipos de habitacion.
     */
    public BDPaquetes(String codigoPais) {
        paquetes = new Paquete[]{new Paquete(1, 200, codigoPais),
                new Paquete(2, 250, codigoPais),
                new Paquete(3, 300, codigoPais)

        };
    }

    /**
     * Metodo que regresa el paquete en la posicion recibida.
     * @param posicion el indice de la habitacion a regresar.
     * @return La habitacion en la posicion recibida.
     */
    public Paquete getPaquete(int posicion){
        int i = posicion - 1;
        return paquetes[i].clone();
    }

}
