package com.EquipoQueNoAceptaMasIntegrantes.Controladores;

import com.EquipoQueNoAceptaMasIntegrantes.Modelo.repositorios.RepositorioUsuario;
import com.EquipoQueNoAceptaMasIntegrantes.Vista.VistaLogin;
import com.EquipoQueNoAceptaMasIntegrantes.Vista.VistaMenuGeneral;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ControladorInicio implements ActionListener{
    private VistaLogin vista;
    private RepositorioUsuario modelo;


   // public RepositorioUsuario modelo=RepositorioUsuario.getInstance();

    public ControladorInicio(VistaLogin vista, RepositorioUsuario modelo) throws IOException {
        this.vista=vista;
        this.modelo=modelo;
        this.vista.addCalculationListener(this);
    }


    public void actionPerformed(ActionEvent actionEvent) {
        String nombre;


            nombre=vista.getUsuario();
            if(modelo.buscarPorUser(nombre) !=null) {
               this.vista.setVisible(false);
                VistaMenuGeneral vista = new VistaMenuGeneral();
                ControladorMenuGeneral controlador= new ControladorMenuGeneral(vista);
                vista.setVisible(true);
            }else{
                vista.metodoPruebaDeLogin("No coincide usuario");
            }
        }






}
