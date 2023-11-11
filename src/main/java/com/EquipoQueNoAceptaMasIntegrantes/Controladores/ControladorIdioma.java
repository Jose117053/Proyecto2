package com.EquipoQueNoAceptaMasIntegrantes.Controladores;

import com.EquipoQueNoAceptaMasIntegrantes.Controlador.util.Mensajes;
import com.EquipoQueNoAceptaMasIntegrantes.Modelo.repositorios.RepositorioUsuario;
import com.EquipoQueNoAceptaMasIntegrantes.Vista.AhoraSi;
import com.EquipoQueNoAceptaMasIntegrantes.Vista.Idiomas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Properties;

public class ControladorIdioma implements ActionListener {

    private Idiomas vista;
    public static Properties msg;

    public ControladorIdioma(Idiomas vista, Properties msg){
        this.vista=vista;
        ControladorIdioma.msg =msg;
        this.vista.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String idioma= actionEvent.getActionCommand();
        try {
            if (idioma.equals("espaniol"))
                msg = Mensajes.cargarMensajes("MX");
            else
                msg = Mensajes.cargarMensajes("US");
        }catch (IOException e){
            System.out.println("kjlfdjklfdjslkf");
        }
        this.vista.prueba();
    }
}
