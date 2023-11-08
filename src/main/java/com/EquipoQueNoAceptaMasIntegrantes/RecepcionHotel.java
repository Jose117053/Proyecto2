package com.EquipoQueNoAceptaMasIntegrantes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import com.EquipoQueNoAceptaMasIntegrantes.Modelo.repositorios.*;
import com.EquipoQueNoAceptaMasIntegrantes.Modelo.objetos.Usuario;
import com.EquipoQueNoAceptaMasIntegrantes.Modelo.objetos.GestorDeOfertas;
import com.EquipoQueNoAceptaMasIntegrantes.Modelo.objetos.Oferta;

public class RecepcionHotel {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RepositorioUsuario repositorioUsuario = RepositorioUsuario.getInstance();
        RepositorioOferta repositorioOferta = RepositorioOferta.getInstance();

        // Obtiene la lista de ofertas disponibles
        List<Oferta> ofertasDisponibles = new ArrayList<>(repositorioOferta.findAll());

        System.out.println("Bienvenido al sistema de reservaciones de hotel.");
        boolean acceso = false;
        Usuario usuario = null;

        while (!acceso) {
            System.out.println("Por favor, ingrese su nombre de usuario:");
            String username = scanner.nextLine();
            System.out.println("Por favor, ingrese su contraseña:");
            String password = scanner.nextLine();

            usuario = repositorioUsuario.buscarPorUser(username);

            if (usuario != null && usuario.getPassword().equals(password)) {
                acceso = true;
                System.out.println("Acceso concedido. Bienvenido " + usuario.getNombre());

                // Si el usuario ha accedido y hay ofertas disponibles, muestra una al azar.
                if (!ofertasDisponibles.isEmpty()) {
                    Oferta ofertaRandom = obtenerOfertaAleatoria(ofertasDisponibles);
                    System.out.println("¡Tenemos una oferta para ti! " + ofertaRandom);

                }
            } else {
                System.out.println("Nombre de usuario o contraseña incorrectos. Intente nuevamente.");
            }
        }

        // Cierre de recursos y lógica posterior...
        scanner.close();
    }

    private static Oferta obtenerOfertaAleatoria(List<Oferta> ofertas) {
        Random random = new Random();
        return ofertas.get(random.nextInt(ofertas.size()));
    }

}