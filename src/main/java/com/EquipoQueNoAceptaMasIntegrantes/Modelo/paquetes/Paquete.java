package com.EquipoQueNoAceptaMasIntegrantes.Modelo.paquetes;

import java.io.IOException;
import lombok.Getter;

@Getter
public abstract class Paquete {

    /* El precio del paquete */
    int precio;
    int id;

    /**
     * Metodo abstracto que devuelve la descripcion del paquete
     * en el idioma del pais del cliente.
     * 
     * @param codigoPais el pais del cliente
     * @return la descripcion del paquete
     * @throws IOException
     */

    public abstract String descripcion(String codigoPais) throws IOException;

}
