package com.EquipoQueNoAceptaMasIntegrantes;

import java.io.IOException;

import com.EquipoQueNoAceptaMasIntegrantes.Controladores.repositorios.*;
import com.EquipoQueNoAceptaMasIntegrantes.Modelo.objetos.GeneradorOfertas;
import com.EquipoQueNoAceptaMasIntegrantes.Vista.HotelFacade;
import  java.util.regex.Pattern;
import  java.util.regex.Matcher;

public class RecepcionHotel {
    public static void main(String[] args) throws IOException {
        ////////////////////////////////////////
        // VistaIdioma vista=new VistaIdioma();
        // Properties msg = new Properties();
        // ControladorIdioma controlador = new ControladorIdioma(vista, msg);
        // vista.setVisible(true);
        //////////////////////////////////////
        RepositorioOferta repositorioOferta = RepositorioOferta.getInstance();
        RepositorioHabitacion repositorioHabitacion = RepositorioHabitacion.getInstance();
        GeneradorOfertas generadorOfertas = new GeneradorOfertas(repositorioOferta, repositorioHabitacion);

        HotelFacade hotelFacade = new HotelFacade(repositorioOferta, repositorioHabitacion, generadorOfertas);

        hotelFacade.reservar();
    }

    private static boolean cadenaFechaValida(String fecha) {
        String patronFecha = "^(0[1-9]|[1-2][0-9]|3[0-1])/(0[1-9]|1[0-2])/\\d{4}$";
        // Compila el patrón de expresión regular
        Pattern patron = Pattern.compile(patronFecha);
        // Crea un objeto Matcher para la cadena de fecha
        Matcher matcher = patron.matcher(fecha);
        return matcher.matches();
    }
}