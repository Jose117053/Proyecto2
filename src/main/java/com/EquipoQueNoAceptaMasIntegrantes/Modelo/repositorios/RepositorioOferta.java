package com.EquipoQueNoAceptaMasIntegrantes.Modelo.repositorios;

import com.EquipoQueNoAceptaMasIntegrantes.Modelo.interfaces.*;
import com.EquipoQueNoAceptaMasIntegrantes.Modelo.objetos.Oferta;
import com.EquipoQueNoAceptaMasIntegrantes.Modelo.repositorios.RepositorioUsuario;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RepositorioOferta implements Repositorio<Oferta>, Sujeto {

    private static volatile RepositorioOferta uniqueInstance;
    private final RepositorioUsuario repositorioUsuario;
    private List<Oferta> ofertas;

    private RepositorioOferta() {
        ofertas = new ArrayList<>();
        repositorioUsuario = RepositorioUsuario.getInstance();
    }

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

    public void guardar(Oferta oferta) {
        ofertas.add(oferta);
        notificar(oferta); // Notificar a todos los observadores que se ha añadido una nueva oferta
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

    @Override
    public void notificar(Object oferta) {
        if (oferta instanceof Oferta) {
            repositorioUsuario.findAll()
                    .forEach(u -> u.actualizar(oferta));
        }
    }
}
