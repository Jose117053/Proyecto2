package com.EquipoQueNoAceptaMasIntegrantes.Modelo.habitaciones;

public class GeneradorDeHabitaciones {
    public Habitacion getHabitacionHotel(BDHabitacion bd, int tipoHabitacion, int numeroHabitacion) {
        // try catchear el int recibido en main
        Habitacion habitacion = bd.getHabitacion(tipoHabitacion);
        habitacion.setNumero(numeroHabitacion);
        return habitacion;
    }
}
