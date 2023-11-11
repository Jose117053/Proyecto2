package com.EquipoQueNoAceptaMasIntegrantes.Modelo.paquetes;

import java.io.IOException;
import lombok.Getter;

@Getter
/** La clase abstracta que modela los paquetes. */
public abstract class Paquete {

    /* El precio del paquete. */
    int precio;
    /* El identificador del paquete. */
    int id;
    /* El código del país. */
    String codigoPais;

    /**
     * Constructor de un paquete.
     * @param codigoPais el código del país.
     */
    public Paquete(String codigoPais) {
        this.codigoPais = codigoPais;
    }

    /**
     * Metodo abstracto que devuelve la descripcion del paquete en el idioma del pais del cliente.
     * @param codigoPais el pais del cliente
     * @return la descripcion del paquete
     * @throws IOException
     */
    public abstract String descripcion(String codigoPais) throws IOException;
}
