package com.EquipoQueNoAceptaMasIntegrantes.Modelo.objetos;

import com.EquipoQueNoAceptaMasIntegrantes.Modelo.interfaces.Observador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import lombok.Getter;
import lombok.Setter;

/**
 * Clase que representa a un usuario
 * Implementa Cloneable, Serializable
 * 
 */
@Getter
@Setter
public class Usuario implements Serializable, Observador {

    private Long id;
    private String username;
    private String password;
    private String nombre;
    private String codigoPais;
    private Collection<Oferta> ofertasDisponibles;

    /**
     * Constructor para inicializar un nuevo usuario.
     * 
     * @param username   Nombre de usuario (nickname).
     * @param password   Contraseña asociada a la cuenta del usuario.
     * @param nombre     Nombre real del usuario.
     * @param codigoPais Código que identifica el país del usuario.
     * @throws InterruptedException si hay una interrupción durante la generación de
     *                              ID.
     */
    public Usuario(
            String username,
            String password,
            String nombre,
            String codigoPais)
            throws InterruptedException {

        this.id = generarID();
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.codigoPais = codigoPais;
        this.ofertasDisponibles = new ArrayList<>();
    }

    @Override
    public void actualizar(Object oferta) {
        if (oferta instanceof Oferta) {
            this.ofertasDisponibles.add((Oferta) oferta);
            System.out.println(nombre + "Recibió una oferta");
        }
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
        return String.format(
                "Usuario [id=%d, username=%s, nombre=%s, codigoPais=%s]",
                id, username, nombre, codigoPais);
    }
}
