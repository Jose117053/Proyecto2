package com.EquipoQueNoAceptaMasIntegrantes.Modelo.objetos;

import com.EquipoQueNoAceptaMasIntegrantes.Modelo.interfaces.Observador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import lombok.Getter;
import lombok.Setter;

/**
 * Clase que representa a un usuario.
 * Implementa Cloneable, Serializable.
 */
@Getter
@Setter
public class Usuario implements Serializable, Observador {

    /* El identificador del usuario. */
    private Long id;
    /* El nombre de usuario del usuario. */
    private String username;
    /* La contraseña del usuario. */
    private String password;
    /* El nombre de pila del usuario. */
    private String nombre;
    /* La colección de ofertas. */
    private Collection<Oferta> ofertasDisponibles;

    /**
     * Constructor para inicializar un nuevo usuario.
     * @param username   Nombre de usuario (nickname).
     * @param password   Contraseña asociada a la cuenta del usuario.
     * @param nombre     Nombre real del usuario.
     * @param codigoPais Código que identifica el país del usuario.
     * @throws InterruptedException si hay una interrupción durante la generación de ID.
     */
    public Usuario(
            String username,
            String password,
            String nombre)
            throws InterruptedException {
        this.id = generarID();
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.ofertasDisponibles = new ArrayList<>();
    }

    /**
     * Actualiza las ofertas.
     */
    @Override
    public void actualizar(Object oferta) {
        if (oferta instanceof Oferta) {
            this.ofertasDisponibles.add((Oferta) oferta);
        }
    }

    /**
     * Genera un identificador único basado en el tiempo actual.
     * @return ID único basado en el tiempo actual.
     * @throws InterruptedException si el proceso es interrumpido.
     */
    public static Long generarID() throws InterruptedException {
        Thread.sleep(100);
        return System.currentTimeMillis();
    }

    /**
     * Representación en cadena del usuario.
     * @return la cadena que representa al usuario.
     */
    @Override
    public String toString() {
        return String.format(
                "Usuario [id=%d, username=%s, nombre=%s]",
                id, username, nombre);
    }
}
