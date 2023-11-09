package com.EquipoQueNoAceptaMasIntegrantes.Modelo.objetos;

import com.EquipoQueNoAceptaMasIntegrantes.Modelo.habitaciones.Habitacion;
import com.EquipoQueNoAceptaMasIntegrantes.Modelo.objetos.*;
import com.EquipoQueNoAceptaMasIntegrantes.Modelo.repositorios.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

//Clase que genera ofertas aleatorias
public class GeneradorOfertas {

    public static final int SEPARACION_OFERTAS = 16000;
    public static final String DESCUENTO = "0.15";
    public static final long EXPIRACION_OFERTA = 30L;
    private RepositorioOferta repositorioOferta;
    private RepositorioHabitacion repositorioHabitacion;

    private List<String> departamentos = List.of("Normal", "Suite", "GrandSuite");
    private List<String> codigosPaises = List.of("MX", "US");
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
                            "\nOferta generada para los usuarios con codigo de pais " + oferta.getCodigoPaisOferta());
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
        // Seleccionar un departamento de manera aleatoria.
        int indexDepartamento = random.nextInt(departamentos.size());
        String departamentoSeleccionado = departamentos.get(indexDepartamento);
        Collection<Habitacion> habitacionesCollection = repositorioHabitacion.buscarPorNombre(departamentoSeleccionado);

        if (habitacionesCollection.isEmpty()) {
            throw new Exception("No hay habitaciones disponibles en el departamento: " + departamentoSeleccionado);
        }

        // Seleccionar una habitación aleatoria del departamento seleccionado.
        List<Habitacion> habitacionesList = new ArrayList<>(habitacionesCollection);
        int randomIndexHabitacion = random.nextInt(habitacionesList.size());
        Habitacion habitacionSeleccionada = habitacionesList.get(randomIndexHabitacion);

        // Crear la oferta con la habitación seleccionada.
        Oferta oferta = new Oferta(
                habitacionSeleccionada.getNombre(), // Suponiendo que Habitación tiene un getter para nombre
                "Detalle generico o algo específico de la habitación", // Deberás definir el detalle aquí
                Double.parseDouble(DESCUENTO));

        // Seleccionar un código de país de manera aleatoria.
        int indexCodigoPais = random.nextInt(codigosPaises.size());
        oferta.setCodigoPaisOferta(codigosPaises.get(indexCodigoPais));

        return oferta;
    }

}
