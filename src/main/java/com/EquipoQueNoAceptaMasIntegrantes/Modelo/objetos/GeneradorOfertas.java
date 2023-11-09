package com.EquipoQueNoAceptaMasIntegrantes.Modelo.objetos;

import com.EquipoQueNoAceptaMasIntegrantes.Modelo.habitaciones.Habitacion;
import com.EquipoQueNoAceptaMasIntegrantes.Modelo.repositorios.*;
import java.util.Collection;
import java.util.List;
import java.util.Random;

//Clase que genera ofertas aleatorias
public class GeneradorOfertas {

    public static final int SEPARACION_OFERTAS = 16000;
    private RepositorioOferta repositorioOferta;
    private RepositorioHabitacion repositorioHabitacion;

    private List<String> departamentos = List.of("Normal", "Suite", "GrandSuite");
    private Random random = new Random();

    public GeneradorOfertas(RepositorioOferta repositorioOferta, RepositorioHabitacion repositorioHabitacion) {
        this.repositorioOferta = repositorioOferta;
        this.repositorioHabitacion = repositorioHabitacion;
    }

    public void simularCreadorOferta() {
        new Thread(() -> {
            do {
                try {
                    Thread.sleep(SEPARACION_OFERTAS);
                    Oferta oferta = crearOfertaRandom();
                    repositorioOferta.guardar(oferta);
                    System.out.println(
                            "\nOferta generada para todos los usuarios");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("Interrupted exception");
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Exception");
                }
            } while (true);
        }).start();
    }

    public Oferta crearOfertaRandom() throws Exception {
        // Seleccionar un tipo de habitación de manera aleatoria.
        int indexTipo = random.nextInt(departamentos.size());
        String tipoHabitacionSeleccionado = departamentos.get(indexTipo);

        // Verificar si hay habitaciones disponibles del tipo seleccionado.
        Collection<Habitacion> habitacionesDelTipo = repositorioHabitacion.buscarPorNombre(tipoHabitacionSeleccionado);
        if (habitacionesDelTipo.isEmpty()) {
            throw new Exception("No hay habitaciones disponibles para el tipo: " + tipoHabitacionSeleccionado);
        }

        // Generar un descuento aleatorio entre 0.1 y 0.3
        double descuentoAleatorio = 0.1 + (0.2) * random.nextDouble();
        descuentoAleatorio = Math.round(descuentoAleatorio * 100.0) / 100.0; // Redondear a dos decimales

        // Lista de descripciones posibles
        List<String> descripciones = List.of(
                "Descuento especial de temporada",
                "Promoción limitada",
                "Oferta por tiempo limitado",
                "Precio especial para reservaciones anticipadas",
                "Última oportunidad de descuento");

        // Seleccionar una descripción aleatoria de la lista
        String descripcionAleatoria = descripciones.get(random.nextInt(descripciones.size()));

        // Crear una oferta para ese tipo de habitación.
        Oferta oferta = new Oferta(
                tipoHabitacionSeleccionado, // Este es el nombre del tipo de habitación al que se aplica la oferta
                descripcionAleatoria, // Descripción aleatoria seleccionada de la lista
                descuentoAleatorio); // Descuento aleatorio generado

        return oferta;
    }

}
