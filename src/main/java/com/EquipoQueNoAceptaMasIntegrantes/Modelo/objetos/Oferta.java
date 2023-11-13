package com.EquipoQueNoAceptaMasIntegrantes.Modelo.objetos;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

/**
 * Clase que representa una oferta sobre un producto con un descuento
 * determinado.
 * Es válida hasta una fecha especificada y aplica para un país en particular.
 */
@Getter
@Setter
@AllArgsConstructor
public class Oferta {

    /* El identificador de la oferta. */
    private Long id;
    /* El nombre de la habitación. */
    private String nombreHabitacion;
    /* El detalle de la oferta. */
    private String detalle;
    /* El porcentaje de descuento de la oferta. */
    private double porcentajeDescuento;
    /* El código del país. */
    private String codigoPais;

    /**
     * Constructor por defecto que genera un ID para la oferta.
     * 
     * @throws InterruptedException si el proceso de generación de ID es
     *                              interrumpido.
     */
    public Oferta() throws InterruptedException {
        this.id = generarID();
    }

    /**
     * Constructor con parámetros.
     * 
     * @param nombreHabitacion El nombre de la habitación.
     * @param detalle          El detalle de la oferta.
     * @param porcentaje       El porcentaje de descuento de la oferta.
     * @param codigoPais       El código del país.
     * @throws InterruptedException
     */
    public Oferta(String nombreHabitacion, String detalle, double porcentaje, String codigoPais)
            throws InterruptedException {
        this.nombreHabitacion = nombreHabitacion;
        this.detalle = detalle;
        this.porcentajeDescuento = porcentaje;
        this.codigoPais = codigoPais;
    }

    /**
     * Genera un identificador único basado en el tiempo actual.
     * 
     * @return ID único basado en el tiempo actual.
     * @throws InterruptedException si el proceso es interrumpido.
     */
    public static Long generarID() throws InterruptedException {
        Thread.sleep(100);
        return System.currentTimeMillis();
    }

    /**
     * Representación en cadena de la oferta.
     * 
     * @return la oferta en cadena.
     */
    @Override
    public String toString() {
        return String.format("<html>Oferta [Habitación=%s, %s, Descuento=%s]<html>",
                nombreHabitacion, detalle, porcentajeDescuento);
    }
}
