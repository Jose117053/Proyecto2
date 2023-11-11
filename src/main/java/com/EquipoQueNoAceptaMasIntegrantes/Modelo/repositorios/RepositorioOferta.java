package com.EquipoQueNoAceptaMasIntegrantes.Modelo.repositorios;

import com.EquipoQueNoAceptaMasIntegrantes.Modelo.interfaces.*;
import com.EquipoQueNoAceptaMasIntegrantes.Modelo.objetos.Oferta;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/** Implementación del repositorio de ofertas. */
public class RepositorioOferta implements Repositorio<Oferta>, Sujeto {

    /* La única instancia de RepositorioOferta. */
    private static volatile RepositorioOferta uniqueInstance;
    /* El repositorio de usuarios. */
    private final RepositorioUsuario repositorioUsuario;
    /* La lista de ofertas disponibles. */
    private List<Oferta> ofertas;

    /**
     * Constructor de RepositorioOferta.
     */
    private RepositorioOferta() {
        ofertas = new ArrayList<>();
        repositorioUsuario = RepositorioUsuario.getInstance();
    }

    /**
     * Método que devuelve la única instancia de RepositorioOferta.
     * @return la única instancia de RepositorioOferta.
     */
    public static RepositorioOferta getInstance() {
        if (uniqueInstance == null) {
            synchronized (RepositorioOferta.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new RepositorioOferta();
                }
            }
        }
        return uniqueInstance;
    }

    /**
     * Método que añade una nueva oferta al repositorio de ofertas e informa a los usuarios sobre ella.
     * @param oferta
     */
    public void guardar(Oferta oferta) {
        ofertas.add(oferta);
        notificar(oferta); // Notificar a todos los observadores que se ha añadido una nueva oferta
    }

    /**
     * Devuelve la primera oferta encontrada al buscarla por su identificador.
     * @return la oferta con tal id.
     */
    @Override
    public Oferta find(Long PK) {
        return ofertas.stream()
                .filter(o -> o.getId().equals(PK))
                .findFirst()
                .orElse(null);
    }

    /**
     * Devuelve todas las ofertas que hay en el momento de consulta.
     * @return la lista de todas las ofertas.
     */
    @Override
    public Collection<Oferta> findAll() {
        return ofertas;
    }

    /**
     * Notifica una oferta a todos los usuarios.
     */
    @Override
    public void notificar(Object oferta) {
        if (oferta instanceof Oferta) {
            repositorioUsuario.findAll()
                    .forEach(u -> u.actualizar(oferta));
        }
    }
}
