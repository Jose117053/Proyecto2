package com.EquipoQueNoAceptaMasIntegrantes;

import java.io.IOException;

import com.EquipoQueNoAceptaMasIntegrantes.Controladores.repositorios.*;
import com.EquipoQueNoAceptaMasIntegrantes.Modelo.objetos.GeneradorOfertas;
import com.EquipoQueNoAceptaMasIntegrantes.Vista.HotelFacade;

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