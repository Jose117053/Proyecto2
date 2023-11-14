package com.EquipoQueNoAceptaMasIntegrantes;

import java.io.IOException;

import com.EquipoQueNoAceptaMasIntegrantes.Controladores.repositorios.*;
import com.EquipoQueNoAceptaMasIntegrantes.Modelo.objetos.GeneradorOfertas;
import com.EquipoQueNoAceptaMasIntegrantes.Vista.HotelFacade;

public class RecepcionHotel {
    public static void main(String[] args) throws IOException {
        RepositorioOferta repositorioOferta = RepositorioOferta.getInstance();
        RepositorioHabitacion repositorioHabitacion = RepositorioHabitacion.getInstance();
        GeneradorOfertas generadorOfertas = new GeneradorOfertas(repositorioOferta, repositorioHabitacion);
        HotelFacade hotelFacade = new HotelFacade(repositorioOferta, repositorioHabitacion, generadorOfertas);

        hotelFacade.reservar();
    }
}