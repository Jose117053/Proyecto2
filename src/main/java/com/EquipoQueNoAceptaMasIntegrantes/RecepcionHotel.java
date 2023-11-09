package com.EquipoQueNoAceptaMasIntegrantes;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import com.EquipoQueNoAceptaMasIntegrantes.Modelo.repositorios.*;
import com.EquipoQueNoAceptaMasIntegrantes.Modelo.objetos.Usuario;
import com.EquipoQueNoAceptaMasIntegrantes.Controlador.util.Mensajes;
import com.EquipoQueNoAceptaMasIntegrantes.Modelo.objetos.GeneradorOfertas;
import com.EquipoQueNoAceptaMasIntegrantes.Modelo.objetos.Oferta;

public class RecepcionHotel {
    public static void main(String[] args) throws InterruptedException, IOException {
        Scanner scanner = new Scanner(System.in);
        RepositorioUsuario repositorioUsuario = RepositorioUsuario.getInstance();
        RepositorioOferta repositorioOferta = RepositorioOferta.getInstance();
        RepositorioHabitacion repositorioHabitacion = RepositorioHabitacion.getInstance();
        GeneradorOfertas generadorOfertas = new GeneradorOfertas(repositorioOferta, repositorioHabitacion);
        String codigoPais = null;
        Properties msg = null;

        while (msg == null) {
            System.out.println("Elige tu país / Choose your country (MX/US):");
            codigoPais = scanner.nextLine().trim().toUpperCase();

            // Solo permitir MX o US como entrada válida
            if (!codigoPais.equals("MX") && !codigoPais.equals("US")) {
                System.out.println("Selecciona entre MX y US / Choose between MX and US");
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
        RepositorioPaquete repositorioPaquete = RepositorioPaquete.getInstance(codigoPais);

        boolean acceso = false;
        Usuario usuario = null;

        // Attempt to log in
        while (!acceso) {
            System.out.println(msg.getProperty("msg.loginUser"));
            String username = scanner.nextLine();
            System.out.println(msg.getProperty("msg.loginPass"));
            String password = scanner.nextLine();

            usuario = repositorioUsuario.buscarPorUser(username);

            if (usuario != null && usuario.getPassword().equals(password)) {
                acceso = true;
                System.out.println(msg.getProperty("msg.bienvenida") + usuario.getNombre());
            } else {
                System.out.println(msg.getProperty("msg.errorLogin"));
            }
        }
        generadorOfertas.simularCreadorOferta(codigoPais);

        boolean sesionActiva = true;
        while (sesionActiva) {
            System.out.println(msg.getProperty("msg.menuReservas"));
            int opcionUsuario = scanner.nextInt();

            switch (opcionUsuario) {
                case 1:
                    repositorioHabitacion.findAll().forEach(System.out::println);
                    break;
                case 2:
                    // Lógica para ver ofertas y promociones especiales
                    List<Oferta> ofertas = (List<Oferta>) repositorioOferta.findAll();
                    if (ofertas.isEmpty()) {
                        System.out.println(msg.getProperty("msg.sinOfertas"));
                    } else {
                        ofertas.forEach(System.out::println);
                    }
                    break;
                case 3:
                    repositorioPaquete.findAll().forEach(System.out::println);
                    break;
                case 4:
                    sesionActiva = false;
                    System.out.println(msg.getProperty("msg.despedida"));
                    break;
                default:
                    // Opción no válida, manejo de error
                    break;
            }
        }
        scanner.close();
    }
    // Cualquier lógica adicional para cerrar aplicaciones o recursos va aquí.

}