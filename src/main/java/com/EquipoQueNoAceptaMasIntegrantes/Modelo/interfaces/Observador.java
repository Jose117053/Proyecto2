package com.EquipoQueNoAceptaMasIntegrantes.Modelo.interfaces;

/**
 * Notificado por un Sujeto ante un evento o cambio.
 *
 * @param evento El evento notificado por el Sujeto.
 */
public interface Observador {
    void actualizar(Object evento);
}
