package com.EquipoQueNoAceptaMasIntegrantes.Modelo.interfaces;

/** Interfaz Sujeto. */
public interface Sujeto {

    /**
     * Registra un observador para recibir notificaciones.
     * 
     * @param observador El observador que se registra.
     */
    void registrar(Observador observador);

    /**
     * Elimina un observador de la lista de notificaciones.
     * 
     * @param observador El observador que se elimina.
     */
    void eliminar(Observador observador);

    /**
     * Notificado por un Sujeto ante una oferta.
     * 
     * @param oferta La oferta notificada por el Sujeto.
     */
    void notificar(Object oferta);
}
