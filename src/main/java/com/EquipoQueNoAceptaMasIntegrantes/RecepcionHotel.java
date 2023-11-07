package com.EquipoQueNoAceptaMasIntegrantes;

import java.io.IOException;
import java.util.Properties;

import com.EquipoQueNoAceptaMasIntegrantes.util.Mensajes;

public class RecepcionHotel {
    public static void main(String[] args) throws IOException {

        Properties msg = Mensajes.cargarMensajes("MX");

        System.out.println(msg.getProperty("msg.bienvenida"));
        Properties msg2 = Mensajes.cargarMensajes("US");
        System.out.println(msg2.getProperty("msg.bienvenida"));
        System.out.println("Hola mundoo");
    }
}
