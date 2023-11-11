package com.EquipoQueNoAceptaMasIntegrantes.Controlador.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * La clase Mensajes proporciona métodos utilitarios relacionados con
 * la carga y manipulación de mensajes
 */
public class Mensajes {

    /**
     * Limpia la pantalla de la consola. El comportamiento puede variar dependiendo
     * del sistema operativo y del entorno donde se ejecute el código.
     */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Carga y devuelve las propiedades de mensajes de un archivo determinado
     * por el código del país proporcionado.
     * @param codigoPais El código del país para el cual cargar los mensajes.
     * @return Properties que contiene los mensajes para el país especificado.
     * @throws IOException Si ocurre un problema al cargar el archivo.
     */
    public static Properties cargarMensajes(String codigoPais) throws IOException {
        String path = "/com/EquipoQueNoAceptaMasIntegrantes/msg-" + codigoPais;
        try (InputStream input = Mensajes.class.getResourceAsStream(path)) {
            if (input != null) {
                Properties prop = new Properties();
                prop.load(input);
                return prop;
            } else {
                throw new IOException("No se encontró el archivo de mensajes para el país: " + codigoPais);
            }
        }
    }
}
