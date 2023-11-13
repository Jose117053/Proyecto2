package com.EquipoQueNoAceptaMasIntegrantes.Controladores;

import com.EquipoQueNoAceptaMasIntegrantes.Controladores.repositorios.RepositorioUsuario;
import com.EquipoQueNoAceptaMasIntegrantes.Vista.VistaLogin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ControladorInicio implements ActionListener {
    private VistaLogin vista;
    private RepositorioUsuario modelo;

    // public RepositorioUsuario modelo=RepositorioUsuario.getInstance();

    public ControladorInicio(VistaLogin vista, RepositorioUsuario modelo) throws IOException {
        this.vista = vista;
        this.modelo = modelo;
        this.vista.addCalculationListener(this);
    }

    public void actionPerformed(ActionEvent actionEvent) {
        String nombre;

        try {
            nombre = vista.getUsuario();
            if (modelo.buscarPorUser(nombre) != null) {
                vista.metodoPruebaDeLogin("coincide usario");
            } else {
                vista.metodoPruebaDeLogin("No coincide usuario");
            }
        } catch (Exception e) {
            System.out.println("jfkasjk fdasljk errorcambiar esto a no coinciden nombre");
        }

    }

}
