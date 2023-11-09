package com.EquipoQueNoAceptaMasIntegrantes.Modelo.paquetes;

import com.EquipoQueNoAceptaMasIntegrantes.Controlador.util.Mensajes;

import java.io.IOException;

/**
 * Clase que modela el paquete Cultural Cancun
 */
public class PaqueteCulturalCancun extends Paquete {
    private String codigoPais; // Suponiendo que tienes esto definido en tu clase

    /**
     * Constructor del paquete Cultural Cancun
     */
    public PaqueteCulturalCancun() {
        precio = 250;
        id = 2;
    }

    /**
     * Regresa un String con la descripcion del paquete en el
     * idioma correspondiente
     * 
     * @param codigoPais "MX" si se quiere idioma espaniol, "US"
     *                   si se quiere la descripcion en idioma ingles.
     * @return La descripcion del paquete Cultural en el idioma del
     *         codigo del pais recibido
     * @throws IOException Si el codigo del pais no existe
     */

    @Override
    public String descripcion(String codigoPais) throws IOException {
        return Mensajes.cargarMensajes(codigoPais).getProperty("msg.descripcionCultural");
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
