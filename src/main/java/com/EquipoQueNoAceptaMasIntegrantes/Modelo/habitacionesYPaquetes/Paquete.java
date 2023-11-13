package com.EquipoQueNoAceptaMasIntegrantes.Modelo.habitacionesYPaquetes;

import java.io.IOException;

import com.EquipoQueNoAceptaMasIntegrantes.Controladores.util.Mensajes;

import lombok.Getter;

@Getter
/** La clase que modela los paquetes. */
public class Paquete implements Cloneable {

    /* El precio del paquete. */
    int precio;
    /* El identificador del paquete. */
    int id;
    /* El código del país. */
    String codigoPais;

    /**
     * Constructor de un paquete.
     * 
     * @param codigoPais el código del país.
     * @param precio     el precio del paquete
     * @param codigoPais el codigo del pais del paquete
     */
    public Paquete(int id, int precio, String codigoPais) {
        this.id = id;
        this.precio = precio;
        this.codigoPais = codigoPais;
    }

    /**
     * Metodo que devuelve la descripcion del paquete en el idioma del pais del
     * cliente.
     * 
     * @param codigoPais el pais del cliente
     * @return la descripcion del paquete
     * @throws IOException
     */
    private String descripcion(String codigoPais, String msgPaquete) throws IOException {
        return Mensajes.cargarMensajes(codigoPais).getProperty(msgPaquete);
    }

    public String descripcion() throws IOException {
        switch (id) {
            case 1:
                return descripcion(getCodigoPais(), "msg.descripcionAventura");
            case 2:
                return descripcion(getCodigoPais(), "msg.descripcionCultural");
            case 3:
                return descripcion(getCodigoPais(), "msg.descripcionRelax");
            default:
                return "Elige una opcion valida";
        }
    }

    /**
     * Metodo que clona un paquete ya definido.
     * 
     * @return la habitación clonada.
     */
    @Override
    public Paquete clone() {
        Paquete clon = new Paquete(getId(), getPrecio(), getCodigoPais());
        return clon;
    }
}
