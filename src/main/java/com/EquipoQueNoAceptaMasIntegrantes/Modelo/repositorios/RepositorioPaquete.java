package com.EquipoQueNoAceptaMasIntegrantes.Modelo.repositorios;

import com.EquipoQueNoAceptaMasIntegrantes.Modelo.paquetes.*;
import static com.EquipoQueNoAceptaMasIntegrantes.Modelo.paquetes.FactoryPaquetes.creaPaquete;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RepositorioPaquete implements Repositorio<Paquete> {

    private static volatile RepositorioPaquete uniqueInstance;
    private List<Paquete> paquetes;

    public static RepositorioPaquete getInstance() {
        if (uniqueInstance == null) {
            synchronized (RepositorioPaquete.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new RepositorioPaquete();
                }
            }
        }
        return uniqueInstance;
    }

    private RepositorioPaquete() {
        inicializarPaquetes();
    }

    private void inicializarPaquetes() {
        paquetes = new ArrayList<>();

        paquetes.add(creaPaquete(1));
        paquetes.add(creaPaquete(2));
        paquetes.add(creaPaquete(3));

    }

    @Override
    public Collection<Paquete> findAll() {
        return paquetes;
    }

    @Override
    public Paquete find(Long PK) {
        return paquetes.stream()
                .filter(p -> p.getId() == PK)
                .findFirst()
                .orElse(null);
    }

}
