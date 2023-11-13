package com.EquipoQueNoAceptaMasIntegrantes.Controladores.repositorios;

import com.EquipoQueNoAceptaMasIntegrantes.Modelo.habitacionesYPaquetes.BDPaquetes;
import com.EquipoQueNoAceptaMasIntegrantes.Modelo.habitacionesYPaquetes.GeneradorDeHabitacionesYPaquetes;
import com.EquipoQueNoAceptaMasIntegrantes.Modelo.habitacionesYPaquetes.Paquete;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/** Implementación del repositorio de paquetes turísticos. */
public class RepositorioPaquete implements Repositorio<Paquete> {

    /* La única instancia de RepositorioPaquete. */
    private static volatile RepositorioPaquete uniqueInstance;
    /* La lista de paquetes turísticos. */
    private List<Paquete> paquetes;

    /**
     * Método que devuelve la única instancia de RepositorioPaquete.
     * 
     * @param codigoPais el código del país.
     * @return la única instancia de RepositorioPaquete.
     */
    public static RepositorioPaquete getInstance(String codigoPais) {
        if (uniqueInstance == null) {
            synchronized (RepositorioPaquete.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new RepositorioPaquete(codigoPais);
                }
            }
        }
        return uniqueInstance;
    }

    /*
     * Constructor de RepositorioPaquete.
     */
    private RepositorioPaquete(String codigoPais) {
        inicializarPaquetes(codigoPais);
    }

    /**
     * Método que añade todos los paquetes de un país en una lista.
     * 
     * @param codigoPais
     */
    private void inicializarPaquetes(String codigoPais) {
        BDPaquetes bd = new BDPaquetes(codigoPais);

        GeneradorDeHabitacionesYPaquetes generador = new GeneradorDeHabitacionesYPaquetes();
        paquetes = new ArrayList<>();
        paquetes.add(generador.getPaquete(bd, 1));
        paquetes.add(generador.getPaquete(bd, 2));
        paquetes.add(generador.getPaquete(bd, 3));

    }

    /**
     * Método que regresa la colección de paquetes.
     * 
     * @return la colección de paquetes.
     */
    @Override
    public Collection<Paquete> findAll() {
        return paquetes;
    }

    /**
     * Método que busca un paquete por su identificador.
     * 
     * @return el paquete con tal id.
     */
    @Override
    public Paquete find(Long PK) {
        return paquetes.stream()
                .filter(p -> p.getId() == PK)
                .findFirst()
                .orElse(null);
    }
}
