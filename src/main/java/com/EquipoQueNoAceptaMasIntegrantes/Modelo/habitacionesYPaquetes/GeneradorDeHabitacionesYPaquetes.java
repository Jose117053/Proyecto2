package com.EquipoQueNoAceptaMasIntegrantes.Modelo.habitacionesYPaquetes;

/** Clase que genera los distintos tipos de habitaciones del hotel. */
public class GeneradorDeHabitacionesYPaquetes {

    /**
     * Método que agrega número de habitación.
     * @param bd la base de datos de habitaciones.
     * @param tipoHabitacion el tipo de habitación.
     * @param numeroHabitacion el número de habitación.
     * @return la habitación ahora con número.
     */
    public Habitacion getHabitacionHotel(BDHabitacion bd, int tipoHabitacion, int numeroHabitacion) {
        Habitacion habitacion = bd.getHabitacion(tipoHabitacion);
        habitacion.setNumero(numeroHabitacion);
        return habitacion;
    }

    /**
     * Método que devuelve el paquete dado la base de datos y el tipo del paquete.
     * @param bd
     * @param tipoPaquete
     * @return el paquete buscado.
     */
    public Paquete getPaquete(BDPaquetes bd, int tipoPaquete){
        Paquete paquete= bd.getPaquete(tipoPaquete);
        return paquete;
    }
}
