package com.EquipoQueNoAceptaMasIntegrantes;

import java.io.IOException;
import java.util.Properties;

import com.EquipoQueNoAceptaMasIntegrantes.Controlador.util.Mensajes;
import com.EquipoQueNoAceptaMasIntegrantes.Modelo.habitaciones.BDHabitacion;
import com.EquipoQueNoAceptaMasIntegrantes.Modelo.habitaciones.GeneradorDeHabitaciones;
import com.EquipoQueNoAceptaMasIntegrantes.Modelo.habitaciones.Habitacion;

public class RecepcionHotel {
    public static void main(String[] args) throws IOException {

        Properties msg = Mensajes.cargarMensajes("MX");

        System.out.println(msg.getProperty("msg.bienvenida"));
        Properties msg2 = Mensajes.cargarMensajes("US");
        System.out.println(msg2.getProperty("msg.bienvenida"));
        System.out.println("Hola mundoo");

        /////////////////////////////
        GeneradorDeHabitaciones generador = new GeneradorDeHabitaciones();
        BDHabitacion habitaciones = new BDHabitacion();
        System.out.print(habitaciones.habitacionesDisponibles());
        int tipoHabitacion = 3;// suponiendo que eligen habitacion suite vista al mar
        Habitacion habitacion = generador.getHabitacionHotel(habitaciones, tipoHabitacion, 22);
        System.out.println("Habitacion " + tipoHabitacion + "\n" + habitacion.descripcion());
        /////////////////////////////

    }
}
