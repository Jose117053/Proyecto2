package com.EquipoQueNoAceptaMasIntegrantes.Controladores;

import com.EquipoQueNoAceptaMasIntegrantes.Controlador.util.Mensajes;
import com.EquipoQueNoAceptaMasIntegrantes.Modelo.repositorios.RepositorioUsuario;
import com.EquipoQueNoAceptaMasIntegrantes.Vista.VistaIdioma;
import com.EquipoQueNoAceptaMasIntegrantes.Vista.VistaLogin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Properties;

public class ControladorIdioma implements ActionListener {

    private VistaIdioma vista;
    public static Properties msg;
    public static String codigoPais;

    public ControladorIdioma(VistaIdioma vista, Properties msg) throws IOException {
        this.vista=vista;
        ControladorIdioma.msg =msg;
        this.vista.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String idioma= actionEvent.getActionCommand();
        try {
            if (idioma.equals("Espanol")) {
                codigoPais="MX";
                msg = Mensajes.cargarMensajes("MX");
            }
            else if(idioma.equals("English")) {
                codigoPais="US";
                msg = Mensajes.cargarMensajes("US");
            }
            else {
                codigoPais="BR";
                msg = Mensajes.cargarMensajes("BR");
            }
        }catch (IOException e){
            System.out.println("kjlfdjklfdjslkf");
        }
        try {
            VistaLogin login=new VistaLogin();
            ControladorInicio controlador= new ControladorInicio(login,new RepositorioUsuario());
            this.vista.setVisible(false);
            login.setVisible(true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
