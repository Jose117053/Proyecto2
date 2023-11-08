package com.EquipoQueNoAceptaMasIntegrantes.Modelo.repositorios;

import com.EquipoQueNoAceptaMasIntegrantes.Modelo.interfaces.Sujeto;
import com.EquipoQueNoAceptaMasIntegrantes.Modelo.objetos.Oferta;
import com.EquipoQueNoAceptaMasIntegrantes.Modelo.repositorios.RepositorioUsuario;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Representa un inventario de ofertas, implementando el patrón Singleton.
 * Además, notifica a los usuarios sobre nuevas ofertas.
 * 
 * @see Inventario
 * @see Sujeto
 */
public class RepositorioOferta implements Repositorio<Oferta>, Sujeto {

    private static volatile RepositorioOferta uniqueInstance;
    private final RepositorioUsuario repositorioUsuario;
    private final List<Oferta> ofertas;

    /**
     * Obtiene la única instancia de InventarioOferta.
     * 
     * @return La instancia única de InventarioOferta.
     */
    public static RepositorioOferta getInstance() {
        if (uniqueInstance == null) {
            synchronized (RepositorioUsuario.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new RepositorioOferta();
                }
            }
        }
        return uniqueInstance;
    }

    private RepositorioOferta() {
        ofertas = new ArrayList<>();
        repositorioUsuario = RepositorioUsuario.getInstance();
    }

    /**
     * Guarda una oferta en el inventario y notifica a los usuarios pertinentes.
     * 
     * @param oferta La oferta a guardar.
     */
    public void guardar(Oferta oferta) {
        ofertas.add(oferta);
        notificar(oferta);
    }

    @Override
    public Oferta find(Long PK) {
        return ofertas.stream()
                .filter(o -> o.getId().equals(PK))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Collection<Oferta> findAll() {
        return ofertas;
    }

    /**
     * Notifica a los usuarios del inventario de usuario sobre una nueva oferta.
     * 
     * @param oferta La oferta a notificar, debe ser una instancia de
     * 
     */
    @Override
    public void notificar(Object oferta) {
        if (oferta instanceof Oferta) {
            Oferta res = (Oferta) oferta;
            repositorioUsuario.findAll()
                    .stream()
                    .filter(u -> u.getCodigoPais().equals(res.getCodigoPaisOferta()))
                    .forEach(u -> u.actualizar(oferta));
        }
    }
}
