package com.EquipoQueNoAceptaMasIntegrantes.Modelo.repositorios;

import com.EquipoQueNoAceptaMasIntegrantes.Modelo.habitaciones.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación del repositorio de Habitaciones, utilizando el patrón
 * Singleton.
 * Cada producto se agrupa por departamento.
 * 
 * 
 */
public class RepositorioHabitacion implements Repositorio<Habitacion> {

    private static volatile RepositorioHabitacion uniqueInstance;
    private static List<Habitacion> habitaciones;

    /**
     * Obtiene la única instancia de InventarioProducto.
     * 
     * @return La instancia única de InventarioProducto.
     */
    public static RepositorioHabitacion getInstance() {

        if (uniqueInstance == null) {
            synchronized (RepositorioUsuario.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new RepositorioHabitacion();
                }
            }
        }

        return uniqueInstance;
    }

    private void inicializarHabitaciones() {
        BDHabitacion bdHabitacion = new BDHabitacion();
        GeneradorDeHabitaciones generador = new GeneradorDeHabitaciones();
        habitaciones = new ArrayList<>();

        // Crear 4 habitaciones normales
        for (int i = 101; i <= 104; i++) {
            habitaciones.add(generador.getHabitacionHotel(bdHabitacion, 1, i)); // 1 representa el tipo 'Normal'
        }

        // Crear 2 Suites
        for (int i = 201; i <= 202; i++) {
            habitaciones.add(generador.getHabitacionHotel(bdHabitacion, 2, i)); // 1 representa el tipo 'Normal'
        }

        // Crear 2 Suites
        for (int i = 302; i <= 303; i++) {
            habitaciones.add(generador.getHabitacionHotel(bdHabitacion, 2, i)); // 1 representa el tipo 'Normal'
        }
    }

    public RepositorioHabitacion() {
        inicializarHabitaciones();
    }

    public List<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    /**
     * Encuentra productos por su departamento.
     *
     * @param nombre El nombre del departamento.
     * @return Lista de productos pertenecientes al departamento dado.
     */
    public Collection<Habitacion> buscarPorNombre(String nombre) {
        return habitaciones
                .stream()
                .filter(p -> p.getNombre().equals(nombre))
                .collect(Collectors.toList());
    }

    /**
     * Genera una representación en cadena de una lista de productos.
     *
     * @param nombre Lista de productos a representar.
     * @return Cadena con detalles de los productos.
     */
    public String mostrarNombre(Collection<Habitacion> nombre) {
        String s = "";

        for (Habitacion habitacion : nombre) {
            s += habitacion.toString();
        }

        return s;
    }

    /**
     * Representa todo el catálogo de productos en forma de cadena.
     *
     * @return Cadena con detalles del catálogo completo.
     */
    public String mostrarCatalogo() {
        String s = "";

        s = mostrarNombre(buscarPorNombre("Normal"));
        s += "-----------------------------------\n";
        s += mostrarNombre(buscarPorNombre("Suite"));
        s += "-----------------------------------\n";
        s += mostrarNombre(buscarPorNombre("GrandSuite"));
        return s;
    }

    /**
     * Metodo que regresa la coleccion de todos los productos del catalogo
     *
     * @return Collection<Producto>
     */
    @Override
    public Collection<Habitacion> findAll() {
        return habitaciones;
    }

    @Override
    public Habitacion find(Long PK) {
        Habitacion res = habitaciones
                .stream()
                .filter(p -> PK == p.getNumero())
                .findFirst()
                .orElse(null);
        return res;
    }

    // Métodos para obtener habitaciones por cada nombre.

    public Collection<Habitacion> getNormal() {
        return buscarPorNombre("Normal");
    }

    public Collection<Habitacion> getSuite() {
        return buscarPorNombre("Suite");
    }

    public Collection<Habitacion> getGrandSuite() {
        return buscarPorNombre("GrandSuite");
    }

}
