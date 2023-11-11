package com.EquipoQueNoAceptaMasIntegrantes.Modelo.habitaciones;

/** Clase que genera los distintos tipos de habitaciones del hotel. */
public class GeneradorDeHabitaciones {

    /**
     * Método que agrega número de habitación.
     * @param bd la base de datos de habitaciones.
     * @param tipoHabitacion el tipo de habitación.
     * @param numeroHabitacion el número de habitación.
     * @return la habitación ahora con número.
     */
    public Habitacion getHabitacionHotel(BDHabitacion bd, int tipoHabitacion, int numeroHabitacion) {
        // try catchear el int recibido en main
        Habitacion habitacion = bd.getHabitacion(tipoHabitacion);
        habitacion.setNumero(numeroHabitacion);
        return habitacion;
    }
}
