package com.EquipoQueNoAceptaMasIntegrantes.Modelo.objetos;

import com.EquipoQueNoAceptaMasIntegrantes.Modelo.habitacionesYPaquetes.Habitacion;
import com.EquipoQueNoAceptaMasIntegrantes.Modelo.repositorios.*;
import com.EquipoQueNoAceptaMasIntegrantes.Controlador.util.Mensajes;
import com.EquipoQueNoAceptaMasIntegrantes.Vista.VistaMenuGeneral;
import com.EquipoQueNoAceptaMasIntegrantes.Vista.VistaOfertas;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.Random;

/** Clase que genera ofertas. */
public class GeneradorOfertas {

    /* Tiempo de separación entre cada oferta. */
    public static final int SEPARACION_OFERTAS = 16000;
    /* El repositorio de ofertas. */
    private RepositorioOferta repositorioOferta;
    /* El repositorio de habitaciones. */
    private RepositorioHabitacion repositorioHabitacion;
    /* La lista con los distintos tipos de habitaciones. */
    private List<String> tipo = List.of("Normal", "Suite", "GrandSuite");
    /* un atributo de tipo Random. */
    private Random random = new Random();

    static String notaOferta;

    /**
     * Constructor.
     * 
     * @param repositorioOferta     el repositorio de ofertas.
     * @param repositorioHabitacion el repositorio de habitaciones.
     */
    public GeneradorOfertas(RepositorioOferta repositorioOferta, RepositorioHabitacion repositorioHabitacion) {
        this.repositorioOferta = repositorioOferta;
        this.repositorioHabitacion = repositorioHabitacion;
    }

    private Thread hiloGeneradorOfertas;
    private volatile boolean continuarGenerandoOfertas = true;

    public void detenerGeneradorOfertas() {
        continuarGenerandoOfertas = false;
        if (hiloGeneradorOfertas != null) {
            hiloGeneradorOfertas.interrupt(); // Intenta interrumpir el hilo si está durmiendo o esperando
        }
    }

    public void simularCreadorOferta(String codigoPais, VistaMenuGeneral vistaMenuGeneral) throws IOException {
        Properties msg = Mensajes.cargarMensajes(codigoPais);

        Thread hiloGeneradorOfertas = new Thread(() -> {
            while (continuarGenerandoOfertas) { // Usa la bandera para controlar el bucle
                try {
                    Thread.sleep(SEPARACION_OFERTAS);
                    Oferta oferta = crearOfertaRandom(codigoPais);
                    repositorioOferta.guardar(oferta);
                    vistaMenuGeneral.labelNotificadorDeOfertas(msg.getProperty("msg.ofertaNota"));
                    System.out.println("\n" + msg.getProperty("msg.ofertaNota"));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Restablecer el estado de interrupción
                    vistaMenuGeneral.labelNotificadorDeOfertas("Gneracion de ofertas interrumpida");
                   // System.out.println("Generación de ofertas interrumpida.");
                    break; // Salir del bucle si el hilo es interrumpido
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Ocurrió una excepción al generar ofertas.");
                }
            }
        });
        hiloGeneradorOfertas.start();
    }

    /**
     * Método que genera oferta al azar.
     * 
     * @param codigoPais el código del país.
     * @return la oferta generada al azar.
     * @throws Exception
     */
    public Oferta crearOfertaRandom(String codigoPais) throws Exception {
        Properties msg = Mensajes.cargarMensajes(codigoPais);

        // Seleccionar un tipo de habitación de manera aleatoria.
        int indexTipo = random.nextInt(tipo.size());
        String tipoHabitacionSeleccionado = tipo.get(indexTipo);

        // Verificar si hay habitaciones disponibles del tipo seleccionado.
        Collection<Habitacion> habitacionesDelTipo = repositorioHabitacion.buscarPorNombre(tipoHabitacionSeleccionado);
        if (habitacionesDelTipo.isEmpty()) {
            throw new Exception(msg.getProperty("errorHabitacion"));
        }

        // Generar un descuento aleatorio entre 0.1 y 0.3
        double descuentoAleatorio = 0.1 + (0.2) * random.nextDouble();
        descuentoAleatorio = Math.round(descuentoAleatorio * 100.0) / 100.0; // Redondear a dos decimales

        // Lista de descripciones posibles
        List<String> descripciones = List.of(
                msg.getProperty("msg.oferta1"),
                msg.getProperty("msg.oferta2"),
                msg.getProperty("msg.oferta3"),
                msg.getProperty("msg.oferta4"),
                msg.getProperty("msg.oferta5"));

        // Seleccionar una descripción aleatoria de la lista
        String descripcionAleatoria = descripciones.get(random.nextInt(descripciones.size()));

        // Crear una oferta para ese tipo de habitación.
        Oferta oferta = new Oferta(
                tipoHabitacionSeleccionado, // Este es el nombre del tipo de habitación al que se aplica la oferta
                descripcionAleatoria, // Descripción aleatoria seleccionada de la lista
                descuentoAleatorio, codigoPais); // Descuento aleatorio generado

        return oferta;
    }
}
