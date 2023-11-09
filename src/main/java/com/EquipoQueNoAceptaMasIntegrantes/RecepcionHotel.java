package com.EquipoQueNoAceptaMasIntegrantes;

import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
import com.EquipoQueNoAceptaMasIntegrantes.Modelo.repositorios.*;
import com.EquipoQueNoAceptaMasIntegrantes.Modelo.objetos.Usuario;
import com.EquipoQueNoAceptaMasIntegrantes.Controlador.util.Mensajes;
import com.EquipoQueNoAceptaMasIntegrantes.Modelo.objetos.GeneradorOfertas;

public class RecepcionHotel {
    public static void main(String[] args) throws InterruptedException, IOException {
        Scanner scanner = new Scanner(System.in);
        RepositorioUsuario repositorioUsuario = RepositorioUsuario.getInstance();
        RepositorioOferta repositorioOferta = RepositorioOferta.getInstance();
        RepositorioHabitacion repositorioHabitacion = RepositorioHabitacion.getInstance();
        GeneradorOfertas generadorOfertas = new GeneradorOfertas(repositorioOferta, repositorioHabitacion);
        Properties msg = null;
        while (msg == null) {
            System.out.println("Elige tu país / Choose your country (MX/US):");
            String codigoPais = scanner.nextLine().trim().toUpperCase();

            // Solo permitir MX o US como entrada válida
            if (!codigoPais.equals("MX") && !codigoPais.equals("US")) {
                System.out.println("Selección inválida. Por favor, elige entre MX y US.");
                continue; // Continuar con la siguiente iteración del bucle
            }

            try {
                msg = Mensajes.cargarMensajes(codigoPais);
            } catch (IOException e) {
                // Manejo de la excepción si no se encuentra el archivo de propiedades o no se
                // puede leer
                System.err.println("Error al cargar los mensajes: " + e.getMessage());
            }
        }

        System.out.println(msg.getProperty("msg.bienvenida"));

        boolean acceso = false;
        Usuario usuario = null;

        // Attempt to log in
        while (!acceso) {
            System.out.println(msg.getProperty("msg.loginUser"));
            String username = scanner.nextLine();
            System.out.println("Por favor, ingrese su contraseña:");
            String password = scanner.nextLine();

            usuario = repositorioUsuario.buscarPorUser(username);

            if (usuario != null && usuario.getPassword().equals(password)) {
                acceso = true;
                System.out.println(msg.getProperty("msg.bienvenida") + usuario.getNombre());
            } else {
                System.out.println("Nombre de usuario o contraseña incorrectos. Intente nuevamente.");
            }
        }

        // Inicia la simulación de creación de ofertas
        generadorOfertas.simularCreadorOferta();

        // User logged in successfully
        while (acceso) {
            System.out.println("\nSeleccione una opción:");
            System.out.println("1. Ver ofertas");
            System.out.println("2. Salir");
            System.out.print("Ingrese el número de la opción deseada: ");
            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    System.out.println("Cargando ofertas disponibles...");
                    repositorioOferta.findAll().forEach(System.out::println);
                    break;
                case "2":
                    System.out.println("Saliendo...");
                    acceso = false;
                    break;
                default:
                    System.out.println("Opción no reconocida, por favor intente de nuevo.");
                    break;
            }
        }

        scanner.close();
        // Cualquier lógica adicional para cerrar aplicaciones o recursos va aquí.
    }
}
