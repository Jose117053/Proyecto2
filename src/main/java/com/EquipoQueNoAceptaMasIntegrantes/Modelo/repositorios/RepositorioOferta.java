package com.EquipoQueNoAceptaMasIntegrantes.Modelo.repositorios;

import com.EquipoQueNoAceptaMasIntegrantes.Modelo.interfaces.Observador;
import com.EquipoQueNoAceptaMasIntegrantes.Modelo.interfaces.Sujeto;
import com.EquipoQueNoAceptaMasIntegrantes.Modelo.objetos.Oferta;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RepositorioOferta implements Repositorio<Oferta>, Sujeto {

    private static volatile RepositorioOferta uniqueInstance;
    private List<Oferta> ofertas;
    private List<Observador> observadores; // Lista de observadores (usuarios que se notificarán)

    private RepositorioOferta() {
        ofertas = new ArrayList<>();
        observadores = new ArrayList<>(); // Inicializar la lista de observadores
    }

    private void inicializarOfertas() throws InterruptedException {

        // Suponiendo que Oferta tiene un constructor Oferta(tipoHabitacion, precio)
        Oferta ofertaNormal = new Oferta("Normal", "Solo para ti", 100);
        Oferta ofertaSuite = new Oferta("Suite", "Solo para ti", 50);
        Oferta ofertaGrandSuite = new Oferta("GrandSuite", "Solo para ti", 40);

        Oferta ofertaNormal1 = new Oferta("Normal", "Solo para ti", 100);
        Oferta ofertaSuite2 = new Oferta("Suite", "Solo para ti", 50);
        Oferta ofertaGrandSuite3 = new Oferta("GrandSuite", "Solo para ti", 40);

        // Añadir ofertas a la lista
        ofertas.add(ofertaNormal);
        ofertas.add(ofertaSuite);
        ofertas.add(ofertaGrandSuite);
        ofertas.add(ofertaNormal1);
        ofertas.add(ofertaSuite2);
        ofertas.add(ofertaGrandSuite3);
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
        notificar(); // Notificar a todos los observadores que se ha añadido una nueva oferta
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

    // Método que retorna una oferta aleatoria del repositorio
    public Oferta getRandomOferta() {
        if (this.ofertas.isEmpty()) {
            return null; // o manejar de otra forma si no hay ofertas
        }
        int index = (int) (Math.random() * this.ofertas.size());
        return this.ofertas.get(index);
    }

    @Override
    public void notificar() {
        // Notificar a cada observador la última oferta agregada (si hay alguna)
        Oferta ultimaOferta = (ofertas.isEmpty()) ? null : ofertas.get(ofertas.size() - 1);
        if (ultimaOferta != null) {
            for (Observador observador : observadores) {
                observador.actualizar(ultimaOferta);
            }
        }
    }
}
