package com.EquipoQueNoAceptaMasIntegrantes.Modelo.reservaciones;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que modela el tiempo de las reservaciones.
 */
public class Reservaciones {
    /**
     * Constructor de la clase
     */
    public Reservaciones() {}

    /**
     * Método encargado de obtener una fecha dada por el cliente que resliza la reservación, 
     * que se utilizara para definir el día de su llegada al hotel y el día de salida.
     * @param fecha una representación en cadena de la fecha de ingreso o de salida del hotel
     *              dada por el cliente.
     * @return una fecha en el formato LocalDate para poder manejar el tiempo de estancia de los 
     *          clientes.
     */
    public LocalDate fechaReserva(String fecha) {
        return LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }



    /**
     * Método que nos regresa una lista con todos los días que estará hospedado un cliente en el hotel.
     * @param fechaIngreso
     * @param fechaSalida
     * @return
     */
    public List<LocalDate> diasDeEstancia(LocalDate fechaIngreso, LocalDate fechaSalida) {
        List<LocalDate> intervalo = new ArrayList<>();
        LocalDate fechaActual = fechaIngreso;
        if (fechaIngreso.isAfter(fechaSalida)) {
            throw new IllegalArgumentException("La fecha de inicio debe ser anterior o igual a la fecha de fin");
        }
        while (!fechaActual.isAfter(fechaSalida)) {
            intervalo.add(fechaActual);
            fechaActual = fechaActual.plus(1, ChronoUnit.DAYS);
        }
        return intervalo;
    }

    /**
     * Método que nos dice si la fecha elegida para la reservación esta disponible de una habitación en específico.
     * @param diasElegidos
     * @param diasOcuapados
     */
    public boolean diasDisponibles(List<LocalDate> diasElegidos, List<LocalDate> diasOcuapados) {
        boolean reservaPosible = true;
        for (LocalDate fecha : diasElegidos) {
            if (diasOcuapados.contains(fecha)) {
                reservaPosible = false;
                break;
            }
        }
        return reservaPosible;
    }

    /**
     * Método para agregar una reservación dado la lista de días y verificarlo con la disponibilidad de habitaciones.
     * @param diasElegidos
     * @param listaHabitacion
     */
    public void agregarReservacion(List<LocalDate> diasElegidos, List<LocalDate> listaHabitacion) {
        listaHabitacion.addAll(diasElegidos);
    }

    /**
     * 
     * @param fechaInicio
     * @param dias
     * @return
     */
    public LocalDate fechaSalida(LocalDate fechaInicio, int dias) {
        return fechaInicio.plusDays(dias);
    }

    /**
     * 
     * @param fechaInicio
     * @param fechaFin
     * @return
     */
    public int diasTranscurridos(LocalDate fechaInicio, LocalDate fechaFin) {
        return (int)(ChronoUnit.DAYS.between(fechaInicio, fechaFin));
    }

    /**
     * 
     * @param fecha
     * @return
     */
    public boolean fechaValida(LocalDate fecha) {
        return LocalDate.now().isBefore(fecha);
    }
}