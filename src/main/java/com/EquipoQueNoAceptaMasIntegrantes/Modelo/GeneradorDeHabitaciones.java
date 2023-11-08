package com.EquipoQueNoAceptaMasIntegrantes.Modelo;

public class GeneradorDeHabitaciones {
    public Habitacion getHabitacionHotel(BDHabitacion bd, int tipoHabitacion){
        //try catchear el int recibido en main
        return bd.getHabitacion(tipoHabitacion);
    }
}
