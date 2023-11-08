package com.EquipoQueNoAceptaMasIntegrantes.Modelo;
import java.io.IOException;
public abstract class  Paquete {

    /* El precio del paquete */
    int precio;

    /**
     * Getter del precio del paquete
     * @return El precio del paquete
     */
    protected int getPrecio(){
        return precio;
    }

    /**
     * Metodo abstracto que devuelve la descripcion del paquete
     * en el idioma del pais del cliente.
     * @param codigoPais el pais del cliente
     * @return la descripcion del paquete
     * @throws IOException
     */

    public abstract String descripcion(String codigoPais) throws IOException;


}

