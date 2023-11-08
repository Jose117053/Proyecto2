package com.EquipoQueNoAceptaMasIntegrantes.Modelo.objetos;

import java.util.HashSet;
import java.util.Set;
import com.EquipoQueNoAceptaMasIntegrantes.Modelo.repositorios.*;

public class GestorDeOfertas {
    private Oferta ofertaActual;
    private RepositorioUsuario repositorioUsuario; // Referencia al repositorio de usuarios
    private RepositorioOferta repositorioOferta; // Referencia al repositorio de usuarios

    public GestorDeOfertas() {
        this.repositorioUsuario = RepositorioUsuario.getInstance(); // Obtener la instancia del singleton
    }

    // Método modificado en GestorDeOfertas para notificar una oferta aleatoria a
    // los observadores
    public void notificarObservadores() {
        Oferta ofertaAleatoria = repositorioOferta.getRandomOferta(); // Obtener una oferta aleatoria
        if (ofertaAleatoria != null) {
            for (Usuario usuario : repositorioUsuario.findAll()) {
                usuario.actualizar(ofertaAleatoria); // Notificar la oferta aleatoria
            }
        }
    }

    // Método en GestorDeOfertas para notificar una oferta aleatoria a un único
    // observador (usuario)
    // Método en GestorDeOfertas para notificar una oferta aleatoria a un único
    // observador (usuario)
    public void notificarObservador(Usuario usuario) {
        Oferta ofertaAleatoria = repositorioOferta.getRandomOferta(); // Utilizar el método que hemos añadido antes
        if (ofertaAleatoria != null) {
            usuario.actualizar(ofertaAleatoria); // Notificar la oferta aleatoria directamente al usuario
            System.out.println("Notificación enviada a " + usuario.getNombre() + " con la oferta: " + ofertaAleatoria);
        } else {
            System.out.println("No hay ofertas disponibles en este momento.");
        }
    }

    public void setOferta(Oferta oferta) {
        this.ofertaActual = oferta;
    }

    // Métodos adicionales aquí...
}
