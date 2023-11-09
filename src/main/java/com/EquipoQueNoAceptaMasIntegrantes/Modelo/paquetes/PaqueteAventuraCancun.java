package com.EquipoQueNoAceptaMasIntegrantes.Modelo.paquetes;

import com.EquipoQueNoAceptaMasIntegrantes.Controlador.util.Mensajes;
import java.io.IOException;

/**
 * Clase que modela el paquete Aventura Cancun
 */
public class PaqueteAventuraCancun extends Paquete {
    private String codigoPais; // Suponiendo que tienes esto definido en tu clase

    /**
     * Constructor del paquete Aventura Cancun
     */
    public PaqueteAventuraCancun() {
        precio = 200;
        id = 1;
    }

    /**
     * Regresa un String con la descripcion del paquete en el
     * idioma correspondiente
     * 
     * @param codigoPais "MX" si se quiere idioma espaniol, "US"
     *                   si se quiere la descripcion en ingles.
     * @return La descripcion del paquete Aventura en el idioma
     *         del codigo del pais recibido
     * @throws IOException Si el codigo del pais no existe
     */

    public String descripcion(String codigoPais) throws IOException {

        return Mensajes.cargarMensajes(codigoPais).getProperty("msg.descripcionAventura");
    }

    @Override
    public String toString() {
        try {
            return descripcion(codigoPais); // Utiliza el método descripcion ya existente
        } catch (IOException e) {
            return "Descripción no disponible"; // Manejo de error en caso de que la carga falle
        }
    }

}
