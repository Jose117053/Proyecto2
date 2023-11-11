package com.EquipoQueNoAceptaMasIntegrantes.Modelo.repositorios;

import com.EquipoQueNoAceptaMasIntegrantes.Modelo.habitaciones.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/** Implementación del repositorio de habitaciones. */
public class RepositorioHabitacion implements Repositorio<Habitacion> {

    /* La única instancia de RepositorioHabitación. */
    private static volatile RepositorioHabitacion uniqueInstance;
    /* La lista de habitaciones. */
    private static List<Habitacion> habitaciones;

    /**
     * Obtiene la única instancia de RepositorioHabitacion.
     * @return la instancia única de RepositorioHabitacion.
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

    /**
     * Método auxiliar que crea cierto número de habitaciones y las mete en una lista.
     */
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
            habitaciones.add(generador.getHabitacionHotel(bdHabitacion, 2, i)); // 2 representa el tipo 'Suite'
        }
        // Crear 2 GrandSuites
        for (int i = 301; i <= 302; i++) {
            habitaciones.add(generador.getHabitacionHotel(bdHabitacion, 3, i)); // 3 representa el tipo 'GrandSuite'
        }
    }

    /**
     * Método que genera las habitaciones del hotel y las añade a una lista.
     */
    public RepositorioHabitacion() {
        inicializarHabitaciones();
    }

    /**
     * Método que devuelve la lista de habitaciones.
     * @return la lista de habitaciones.
     */
    public List<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    /**
     * Método que encuentra habitaciones por su nombre.
     * @param nombre el nombre del tipo de habitación.
     * @return lista de habitaciones del tipo buscado.
     */
    public Collection<Habitacion> buscarPorNombre(String nombre) {
        return habitaciones
                .stream()
                .filter(p -> p.getNombre().equals(nombre))
                .collect(Collectors.toList());
    }

    /**
     * Genera una representación en cadena de la lista de habitaciones.
     * @param nombre lista del tipo de habitaciones a representar.
     * @return cadena con detalles de las habitaciones buscadas.
     */
    public String mostrarNombre(Collection<Habitacion> nombre) {
        String s = "";
        for (Habitacion habitacion : nombre) {
            s += habitacion.toString();
        }
        return s;
    }

    /**
     * Representa todas las habitaciones del hotel en una cadena.
     * @return cadena con detalles de todas las habitaciones.
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
     * Método que regresa la coleccion de todas las habitaciones.
     * @return Collection<Habitacion> la colección de todas las habitaciones.
     */
    @Override
    public Collection<Habitacion> findAll() {
        return habitaciones;
    }

    /**
     * Método que busca una habitación por su número.
     * @return la habitación buscada.
     */
    @Override
    public Habitacion find(Long PK) {
        Habitacion res = habitaciones
                .stream()
                .filter(p -> PK == p.getNumero())
                .findFirst()
                .orElse(null);
        return res;
    }

    /**
     * La colección de habitaciones de tipo normal.
     * @return la coleccción de habitaciones normales.
     */
    public Collection<Habitacion> getNormal() {
        return buscarPorNombre("Normal");
    }

    /**
     * La colección de habitaciones de tipo suite.
     * @return la colección de habitaciones suite.
     */
    public Collection<Habitacion> getSuite() {
        return buscarPorNombre("Suite");
    }

    /**
     * La colección de habitaciones de tipo GrandSuite.
     * @return la colección de habitaciones GrandSuite.
     */
    public Collection<Habitacion> getGrandSuite() {
        return buscarPorNombre("GrandSuite");
    }
}
