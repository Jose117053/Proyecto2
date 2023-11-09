package com.EquipoQueNoAceptaMasIntegrantes;

import java.util.Scanner;
import com.EquipoQueNoAceptaMasIntegrantes.Modelo.repositorios.*;
import com.EquipoQueNoAceptaMasIntegrantes.Modelo.objetos.Usuario;
import com.EquipoQueNoAceptaMasIntegrantes.Modelo.objetos.GeneradorOfertas;

public class RecepcionHotel {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        RepositorioUsuario repositorioUsuario = RepositorioUsuario.getInstance();
        RepositorioOferta repositorioOferta = RepositorioOferta.getInstance();
        RepositorioHabitacion repositorioHabitacion = RepositorioHabitacion.getInstance();
        GeneradorOfertas generadorOfertas = new GeneradorOfertas(repositorioOferta, repositorioHabitacion); // Asumiendo
                                                                                                            // que
                                                                                                            // tienes un
                                                                                                            // constructor
                                                                                                            // adecuado

        // Inicia la simulación de creación de ofertas
        generadorOfertas.simularCreadorOferta();

        System.out.println("Bienvenido al sistema de reservaciones de hotel.");
        boolean acceso = false;
        Usuario usuario = null;

        // Attempt to log in
        while (!acceso) {
            System.out.println("Por favor, ingrese su nombre de usuario:");
            String username = scanner.nextLine();
            System.out.println("Por favor, ingrese su contraseña:");
            String password = scanner.nextLine();

            usuario = repositorioUsuario.buscarPorUser(username);

            if (usuario != null && usuario.getPassword().equals(password)) {
                acceso = true;
                System.out.println("Acceso concedido. Bienvenido " + usuario.getNombre());
            } else {
                System.out.println("Nombre de usuario o contraseña incorrectos. Intente nuevamente.");
            }
        }

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
                    repositorioOferta.findAll().forEach(System.out::println); // Assuming toString() method is
                                                                              // overridden in Oferta class
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
