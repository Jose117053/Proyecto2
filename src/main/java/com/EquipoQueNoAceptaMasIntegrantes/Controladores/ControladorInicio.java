package com.EquipoQueNoAceptaMasIntegrantes.Controladores;

import com.EquipoQueNoAceptaMasIntegrantes.Controlador.util.Mensajes;
import com.EquipoQueNoAceptaMasIntegrantes.Modelo.repositorios.RepositorioUsuario;
import com.EquipoQueNoAceptaMasIntegrantes.Vista.AhoraSi;
import com.EquipoQueNoAceptaMasIntegrantes.Vista.segundaVentana;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Properties;

public class ControladorInicio implements ActionListener{
    private AhoraSi vista;
    private RepositorioUsuario modelo;


   // public RepositorioUsuario modelo=RepositorioUsuario.getInstance();

    public ControladorInicio(AhoraSi vista, RepositorioUsuario modelo) throws IOException {
        this.vista=vista;
        this.modelo=modelo;
        this.vista.addCalculationListener(this);
    }


    public void actionPerformed(ActionEvent actionEvent) {
        String nombre;
        try{
            nombre=vista.getUsuario();
            if(modelo.buscarPorUser(nombre) !=null) {
                vista.setVisible(false);
                new segundaVentana().setVisible(true);
            }
        }catch (Exception e){
            System.out.println("jfkasjk fdasljk errorcambiar esto a no coinciden nombre");
        }
    }



}
