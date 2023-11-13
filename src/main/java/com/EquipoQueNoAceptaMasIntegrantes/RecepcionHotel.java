package com.EquipoQueNoAceptaMasIntegrantes;

import java.io.IOException;

import com.EquipoQueNoAceptaMasIntegrantes.Modelo.repositorios.*;
import com.EquipoQueNoAceptaMasIntegrantes.Modelo.objetos.GeneradorOfertas;

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
}