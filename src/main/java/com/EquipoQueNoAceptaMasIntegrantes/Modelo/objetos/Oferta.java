package com.EquipoQueNoAceptaMasIntegrantes.Modelo.objetos;

import java.math.BigDecimal;
import java.util.Date;

import com.EquipoQueNoAceptaMasIntegrantes.Modelo.habitaciones.Habitacion;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

/**
 * Clase que representa una oferta sobre un producto con un descuento
 * determinado
 * que es válida hasta una fecha especificada y aplica para un país en
 * particular.
 */
@Getter
@Setter
@AllArgsConstructor
public class Oferta {

    private Long id;
    private Habitacion habitacion;
    private BigDecimal porcentajeDescuento;
    private String codigoPaisOferta;
    private Date validaHasta;

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
     * Genera un identificador único basado en el tiempo actual.
     * 
     * 
     * @return ID único basado en el tiempo actual.
     * @throws InterruptedException si el proceso es interrumpido.
     */
    public static Long generarID() throws InterruptedException {
        Thread.sleep(100);
        return System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return String.format("Oferta [habitación=%s, descuento=%s, validez=%s]",
                habitacion.getNombre(), porcentajeDescuento, validaHasta);
    }
}
