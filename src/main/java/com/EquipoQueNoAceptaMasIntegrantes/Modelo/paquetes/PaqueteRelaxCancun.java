package com.EquipoQueNoAceptaMasIntegrantes.Modelo.paquetes;

import com.EquipoQueNoAceptaMasIntegrantes.Controlador.util.Mensajes;
import java.io.IOException;

/** Clase que modela el paquete Relax Cancún. */
public class PaqueteRelaxCancun extends Paquete {
    // private String codigoPais; // Suponiendo que tienes esto definido en tu clase

    /**
     * Constructor del paquete Relax Cancun
     * @param codigoPais el código del país.
     */
    public PaqueteRelaxCancun(String codigoPais) {
        super(codigoPais); // Llama al constructor de la superclase con codigoPais
        this.precio = 300;
        this.id = 3;
        // No es necesario asignar this.codigoPais porque ya se asignó en la superclase
    }

    /**
     * Regresa un String con la descripción del paquete en el idioma correspondiente.
     * @param codigoPais  "MX" si se quiere idioma español, "US" si se quiere la descripcion en ingles,"BR" si se quiere la descripcion en portugués.
     * @return La descripción del paquete Relax en el idioma del código del pais recibido.
     * @throws IOException Si el código del pais no existe.
     */
    @Override
    public String descripcion(String codigoPais) throws IOException {
        return Mensajes.cargarMensajes(codigoPais).getProperty("msg.descripcionRelax");
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
