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
    /* Lista para manejar los suscriptores/observadores */
    private List<Observador> observadores;

    /**
     * Constructor de RepositorioOferta.
     */
    private RepositorioOferta() {
        ofertas = new ArrayList<>();
        observadores = new ArrayList<>(); // Inicializa la lista de observadores
        repositorioUsuario = RepositorioUsuario.getInstance();
    }

    /**
     * Método que devuelve la única instancia de RepositorioOferta.
     * 
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
     * Método que añade una nueva oferta al repositorio de ofertas e informa a los
     * usuarios sobre ella.
     * 
     * @param oferta
     */
    public void guardar(Oferta oferta) {
        ofertas.add(oferta);
        notificar(oferta); // Notificar solo si se aceptan nuevas ofertas.
    }

    /**
     * Devuelve la primera oferta encontrada al buscarla por su identificador.
     * 
     * @param PK
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
     * 
     * @return la lista de todas las ofertas.
     */
    @Override
    public Collection<Oferta> findAll() {
        return ofertas;
    }

    /**
     * Registra un nuevo observador para recibir notificaciones de ofertas.
     * 
     * @param observador El observador que se registra.
     */
    @Override
    public void registrar(Observador observador) {
        synchronized (observadores) {
            System.out.println("Lista de observadores antes de registrar: " + observadores);

            if (!observadores.contains(observador)) {
                observadores.add(observador);
                System.out.println("Agregado");
            }
            System.out.println("Lista de observadores después de registrar: " + observadores);

        }
    }

    /**
     * Elimina un observador de la lista de suscriptores.
     * 
     * @param observador El observador que se elimina.
     */
    @Override
    public void eliminar(Observador observador) {
        synchronized (observadores) {
            System.out.println("Lista de observadores antes de eliminar: " + observadores);

            boolean eliminado = observadores.remove(observador);
            if (eliminado) {
                System.out.println("Observador eliminado: " + observador);
            } else {
                System.out.println("Intento de eliminar un observador que no está en la lista.");
            }
            System.out.println("Lista de observadores después de eliminar: " + observadores);

        }
    }

    /**
     * Notifica una oferta a todos los observadores registrados.
     * 
     * @param oferta La oferta que se notifica.
     */
    @Override
    public void notificar(Object oferta) {
        List<Observador> copiaObservadores;
        synchronized (observadores) {
            copiaObservadores = new ArrayList<>(observadores);
        }
        for (Observador o : copiaObservadores) {
            // Verificar si el observador aún está registrado antes de notificar
            synchronized (observadores) {
                if (observadores.contains(o)) {
                    o.actualizar(oferta);
                }
            }
        }
    }
}
