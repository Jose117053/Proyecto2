package com.EquipoQueNoAceptaMasIntegrantes.Modelo.interfaces;

/** Interfaz observador.  */
public interface Observador {

    /**
     * Notifica a los Observadores sobre una oferta.
     * @param oferta La oferta a notificar.
     */
    void actualizar(Object oferta);
}
