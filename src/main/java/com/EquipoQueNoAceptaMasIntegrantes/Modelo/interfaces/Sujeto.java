package com.EquipoQueNoAceptaMasIntegrantes.Modelo.interfaces;

/** Interfaz Sujeto. */
public interface Sujeto {

    /**
     * Notificado por un Sujeto ante una oferta.
     * @param oferta La oferta notificada por el Sujeto.
     */
    void notificar(Object oferta);
}
