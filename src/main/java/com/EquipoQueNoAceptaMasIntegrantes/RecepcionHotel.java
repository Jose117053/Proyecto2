package com.EquipoQueNoAceptaMasIntegrantes;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import com.EquipoQueNoAceptaMasIntegrantes.Controladores.ControladorIdioma;
import com.EquipoQueNoAceptaMasIntegrantes.Modelo.objetos.Oferta;
import com.EquipoQueNoAceptaMasIntegrantes.Modelo.paquetes.Paquete;
import com.EquipoQueNoAceptaMasIntegrantes.Modelo.repositorios.*;
import com.EquipoQueNoAceptaMasIntegrantes.Modelo.objetos.Usuario;
import com.EquipoQueNoAceptaMasIntegrantes.Controlador.util.Correo;
import com.EquipoQueNoAceptaMasIntegrantes.Controlador.util.Mensajes;
import com.EquipoQueNoAceptaMasIntegrantes.Modelo.habitaciones.Habitacion;
import com.EquipoQueNoAceptaMasIntegrantes.Modelo.objetos.GeneradorOfertas;

import com.EquipoQueNoAceptaMasIntegrantes.Vista.AhoraSi;
import com.EquipoQueNoAceptaMasIntegrantes.Vista.Idiomas;

import javax.mail.MessagingException;


public class RecepcionHotel {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        RepositorioOferta repositorioOferta = RepositorioOferta.getInstance();
        RepositorioHabitacion repositorioHabitacion = RepositorioHabitacion.getInstance();
        RepositorioPaquete repositorioPaquete = RepositorioPaquete.getInstance(codigoPais);

        GeneradorOfertas generadorOfertas = new GeneradorOfertas(repositorioOferta, repositorioHabitacion);

        seleccionarIdioma();
        Usuario usuario = login();

        // generadorOfertas.simularCreadorOferta(codigoPais); NO ELIMINAR

        boolean sesionActiva = true;
        while (sesionActiva) {
            System.out.println(msg.getProperty("msg.menuReservas"));

            if (scanner.hasNextInt()) {
                int opcionUsuario = scanner.nextInt();
                switch (opcionUsuario) {
                    case 1:
                        // Mostrar los 3 tipos de habitación
                        break;

                    case 2:

                        System.out.println(msg.getProperty("msg.noches"));
                        int numNoches = 0;
                        boolean nochesValidas = false;

                        while (!nochesValidas) {
                            try {
                                // Intenta leer un número para el número de noches
                                numNoches = scanner.nextInt();

                                if (numNoches > 0 && numNoches <= 10) {
                                    nochesValidas = true; // La entrada es válida, sale del ciclo
                                } else {
                                    System.out.println(msg.getProperty("msg.errorNoches"));
                                }
                            } catch (InputMismatchException e) {

                                System.out.println(msg.getProperty("msg.errorEntrada"));
                                scanner.next();
                            }
                        }

                        System.out.println(msg.getProperty("msg.personas"));
                        int numPersonas = 0;
                        boolean entradaValida = false;

                        // Ciclo que se repite hasta que la entrada es válida
                        while (!entradaValida) {
                            try {
                                // Intenta leer un número
                                numPersonas = scanner.nextInt();

                                // Verifica si el número está en el rango correcto y rompe el ciclo si es
                                // correcto
                                if (numPersonas >= 1 && numPersonas <= 8) {
                                    entradaValida = true;
                                    // Aquí podrías añadir la lógica dependiendo del número de personas
                                } else {
                                    // Si el número no está en el rango, muestra un mensaje de error
                                    System.out.println(msg.getProperty("msg.errorPersonas"));
                                }
                            } catch (InputMismatchException e) {
                                // Si la entrada no es un número, maneja la excepción y limpia el buffer del
                                // scanner
                                System.out.println(
                                        msg.getProperty("msg.errorPersonas"));
                                scanner.next(); // Limpia el buffer del scanner descartando la entrada incorrecta
                            }
                        }
                        TipoHabitacion tipoHabitacionSeleccionado = null; // Inicializar con null para manejar el caso
                                                                          // inesperado

                        // Ahora que tenemos un número válido, continuamos con el flujo del programa
                        // Muestra las habitaciones disponibles basadas en el número de personas
                        if (numPersonas <= 2) {
                            tipoHabitacionSeleccionado = TipoHabitacion.NORMAL;
                            System.out.println(repositorioHabitacion.mostrarNombre(repositorioHabitacion.getNormal()));
                        } else if (numPersonas <= 5) {
                            tipoHabitacionSeleccionado = TipoHabitacion.SUITE;
                            System.out.println(repositorioHabitacion.mostrarNombre(repositorioHabitacion.getSuite()));
                        } else if (numPersonas <= 8) {
                            tipoHabitacionSeleccionado = TipoHabitacion.GRANDSUITE;
                            System.out.println(
                                    repositorioHabitacion.mostrarNombre(repositorioHabitacion.getGrandSuite()));
                        }

                        System.out.println(msg.getProperty("msg.seleccion"));

                        // Lee la entrada del usuario para la selección de habitación o para regresar
                        boolean seleccionValida = false; // Bandera para controlar la validez de la selección de
                                                         // habitación
                        int seleccionHabitacion = -1; // Inicializa la variable fuera del rango de habitaciones válidas

                        while (!seleccionValida) {
                            try {
                                seleccionHabitacion = scanner.nextInt(); // Intenta leer la selección del usuario

                                // Verifica si el usuario desea regresar o si selecciona una habitación válida
                                if (seleccionHabitacion == 0) {
                                    seleccionValida = true;
                                    break;
                                } else if (esNumeroHabitacionValido(seleccionHabitacion, tipoHabitacionSeleccionado)) {
                                    System.out.println(msg.getProperty("msg.preparando"));
                                    seleccionValida = true; // Establece la bandera a verdadero para salir del ciclo
                                    // Aquí iría la lógica para confirmar y procesar la reserva

                                    Habitacion habitacionSeleccionada = repositorioHabitacion
                                            .find((long) seleccionHabitacion);

                                    if (habitacionSeleccionada != null) {
                                        double costoPorNoche = habitacionSeleccionada.getCosto();
                                        double costoTotal = costoPorNoche * numNoches;

                                        // Muestra el costo total al usuario
                                        System.out
                                                .println(msg.getProperty("msg.costoNoches"));
                                        System.out.println(costoTotal + "USD");

                                        // Aquí implementar logica del decorator

                                        System.out.println(msg.getProperty("msg.elegirPaquetes"));
                                        repositorioPaquete.findAll().forEach(System.out::println);

                                        int seleccionPaquete = -1;
                                        double costoPaquete = 0.0;
                                        boolean paqueteValido = false;

                                        // Ciclo para la selección del paquete
                                        while (!paqueteValido) {
                                            try {
                                                seleccionPaquete = scanner.nextInt(); // Intenta leer la elección del

                                                if (seleccionPaquete == 0) {
                                                    paqueteValido = true; // El usuario no quiere agregar un paquete
                                                } else {

                                                    Paquete paqueteSeleccionado = repositorioPaquete
                                                            .find((long) seleccionPaquete);
                                                    if (paqueteSeleccionado != null) {
                                                        costoPaquete = paqueteSeleccionado.getPrecio() * numPersonas;

                                                        paqueteValido = true; // El usuario seleccionó un paquete válido

                                                        System.out
                                                                .println(msg.getProperty("msg.costoPaquete ")
                                                                        + numPersonas
                                                                        + msg.getProperty("msg.personitas")
                                                                        + +costoPaquete
                                                                        + " USD.");

                                                    } else {
                                                        System.out.println(msg.getProperty("msg.errorPaquete"));
                                                    }
                                                }
                                            } catch (InputMismatchException e) {
                                                System.out.println(msg.getProperty("msg.errorEntrada"));
                                                scanner.next(); // Limpia el buffer del scanner
                                            }
                                        }

                                        // Calcula el costo total sumando el costo de la habitación y el paquete
                                        double costoReservacionTotal = costoTotal + costoPaquete;

                                        // Muestra el costo total de la reservación al usuario
                                        System.out.println(msg.getProperty("msg.costoTotal") + costoReservacionTotal
                                                + " USD.");

                                        System.out.println(msg.getProperty("msg.resumen"));
                                        System.out.println(msg.getProperty("msg.resumenHabitacion"));
                                        System.out.println(
                                                habitacionSeleccionada != null ? habitacionSeleccionada.toString()
                                                        : "N/A");
                                        String correoTexto = "Bienvenido a nuestro hotel, " + usuario.getNombre()
                                                + "\nGracias por reservar con nosotros\nA continuación, te dejo los detalles";
                                        correoTexto += msg.getProperty("msg.resumen") + "\nNúmero de noches: "
                                                + numNoches + "\nNúmero de personas: "
                                                + numPersonas + "\n"
                                                + msg.getProperty("msg.resumenHabitacion") + "\n"
                                                + (habitacionSeleccionada != null ? habitacionSeleccionada.toString()
                                                        : "N/A");

                                        // Verificar si se seleccionó un paquete y mostrar detalles
                                        if (seleccionPaquete > 0) {
                                            Paquete paqueteSeleccionado = repositorioPaquete
                                                    .find((long) seleccionPaquete);
                                            System.out.println(msg.getProperty("msg.resumenPaquete"));
                                            System.out.println(
                                                    paqueteSeleccionado != null ? paqueteSeleccionado.toString()
                                                            : "N/A");
                                            System.out.println(msg.getProperty("msg.costoPaquete") + numPersonas
                                                    + msg.getProperty("msg.personitas") + +costoPaquete
                                                    + " USD.");
                                            correoTexto += "\n" + msg.getProperty("msg.resumenPaquete") + "\n"
                                                    + (paqueteSeleccionado != null ? paqueteSeleccionado.toString()
                                                            : "N/A")
                                                    + "\n" + msg.getProperty("msg.costoPaquete") + numPersonas
                                                    + msg.getProperty("msg.personitas") + costoPaquete + " USD.";

                                        }

                                        // Mostrar costo total de la reserva
                                        System.out.println(msg.getProperty("msg.costoTotal") + costoReservacionTotal
                                                + " USD.");
                                        correoTexto += "\n" + msg.getProperty("msg.costoTotal") + costoReservacionTotal
                                                + " USD.";
                                        correoTexto += "Muchas gracias por reservar con nosotros\n Te esperamos pronto para disfrutar unas maravillosas vacaciones";

                                        // Preguntar al usuario si desea confirmar la reserva
                                        System.out.println(msg.getProperty("msg.confirmar"));
                                        int confirmacion = scanner.nextInt();

                                        if (confirmacion == 1) {
                                            // Solicitar correo electrónico y enviar información de la reserva
                                            System.out.println(msg.getProperty("msg.email"));
                                            String email = scanner.next(); // Leer el correo electrónico del usuario
                                            try {
                                                Correo.sendEmail(email, "Reservación Hotel", correoTexto);
                                                System.out.println("Correo enviado exitosamente a: " + email);
                                            } catch (MessagingException e) {
                                                System.out.println(
                                                        "Error al enviar el correo electrónico: " + e.getMessage());
                                            }
                                            System.out.println(msg.getProperty("msg.enviado") + email);
                                        } else {
                                            // Regresar al menú
                                            System.out.println(msg.getProperty("msg.cancelacion"));
                                            break;
                                        }
                                    } else {
                                        // Si no se encuentra la habitación, informa al usuario
                                        System.out.println(msg.getProperty("msg.errorNumero"));
                                    }

                                } else {
                                    // Si la selección no es válida, informa al usuario
                                    System.out.println(msg.getProperty("msg.errorNumero"));
                                }
                            } catch (InputMismatchException e) {
                                // Maneja la excepción si la entrada no es un entero
                                System.out.println(msg.getProperty("msg.errorEntrada"));
                                scanner.next(); // Limpia el buffer del scanner
                            }
                        }

                        break;

                    case 3:
                        // Lógica para ver ofertas y promociones especiales
                        List<Oferta> ofertas = (List<Oferta>) repositorioOferta.findAll();
                        if (ofertas.isEmpty()) {
                            System.out.println(msg.getProperty("msg.sinOfertas"));
                        } else {
                            ofertas.forEach(System.out::println);
                        }
                        break;
                    case 4:
                        repositorioPaquete.findAll().forEach(System.out::println);
                        break;
                    case 5:
                        sesionActiva = false;
                        System.out.println(msg.getProperty("msg.despedida"));
                        break;
                    default:
                        System.out.println(msg.getProperty("msg.errorEntrada"));
                        scanner.nextLine();
                        break;
                }
            } else {
                // Si la entrada no es un número entero, muestra un mensaje de error y limpia el
                // búfer
                System.out.println(msg.getProperty("msg.errorEntrada"));
                scanner.nextLine();
            }
        }
        scanner.close();

    }

    private static Usuario login() {
        RepositorioUsuario repositorioUsuario = RepositorioUsuario.getInstance();
        boolean acceso = false;
        Usuario usuario = null;

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

        return usuario; // retorna el usuario que ha iniciado sesión
    }

    public enum TipoHabitacion {
        NORMAL, SUITE, GRANDSUITE
    }

    private static Scanner scanner = new Scanner(System.in); // Asumiendo que tienes un Scanner para leer la entrada del
    private static String codigoPais; // Accesible globalmente en la clase Main
    private static Properties msg; // Accesible globalmente en la clase Main

    private static void seleccionarIdioma() {
        while (true) {
            System.out.println("Elige tu país / Choose your country / Escolha seu país (MX/US/BR):");
            codigoPais = scanner.nextLine().trim().toUpperCase();

            // Solo permitir MX o US o BR como entrada válida
            if (!codigoPais.equals("MX") && !codigoPais.equals("US") && !codigoPais.equals("BR")) {
                System.out.println("Selecciona entre MX, US y BR / Choose between MX, US and BR / Selecione entre MX, US e BR");
                continue;
            }

            try {
                msg = Mensajes.cargarMensajes(codigoPais);
                System.out.println(msg.getProperty("msg.bienvenida"));
                break; // Sal del ciclo una vez que los mensajes son cargados correctamente
            } catch (IOException e) {
                System.err.println("Error al cargar los mensajes: " + e.getMessage());
            }
        }
    }

    private static boolean esNumeroHabitacionValido(int numeroHabitacion, TipoHabitacion tipo) {
        switch (tipo) {
            case NORMAL:
                return numeroHabitacion >= 101 && numeroHabitacion <= 104;
            case SUITE:
                return numeroHabitacion >= 201 && numeroHabitacion <= 202;
            case GRANDSUITE:
                return numeroHabitacion >= 301 && numeroHabitacion <= 302;
            default:
                return false; // En caso de que el tipo no sea reconocido
        }
    }
}