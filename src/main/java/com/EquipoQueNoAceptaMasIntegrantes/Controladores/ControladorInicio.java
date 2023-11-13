package com.EquipoQueNoAceptaMasIntegrantes.Controladores;

import com.EquipoQueNoAceptaMasIntegrantes.Modelo.objetos.GeneradorOfertas;
import com.EquipoQueNoAceptaMasIntegrantes.Modelo.objetos.Usuario;
import com.EquipoQueNoAceptaMasIntegrantes.Modelo.repositorios.RepositorioHabitacion;
import com.EquipoQueNoAceptaMasIntegrantes.Modelo.repositorios.RepositorioOferta;
import com.EquipoQueNoAceptaMasIntegrantes.Modelo.repositorios.RepositorioUsuario;
import com.EquipoQueNoAceptaMasIntegrantes.Vista.VistaLogin;
import com.EquipoQueNoAceptaMasIntegrantes.Vista.VistaMenuGeneral;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ControladorInicio implements ActionListener{
    private VistaLogin vista;
    private RepositorioUsuario repositorioUsuario;
    public static RepositorioOferta repositorioOferta = RepositorioOferta.getInstance();
    public static GeneradorOfertas generadorOfertas = new GeneradorOfertas(repositorioOferta, RepositorioHabitacion.getInstance());


    Usuario usuario;
    public ControladorInicio(VistaLogin vista, RepositorioUsuario repositorioUsuario) throws IOException {
        this.vista=vista;
        this.repositorioUsuario =repositorioUsuario;
        this.vista.addCalculationListener(this);
    }
    public void actionPerformed(ActionEvent actionEvent) {
        String username;
            username=vista.getUsuario();
            if(repositorioUsuario.buscarPorUser(username) !=null) {
               this.vista.setVisible(false);
               ////////////////////////////////////
                usuario = repositorioUsuario.buscarPorUser(username);
                repositorioOferta.registrar(usuario);

                //////////////////////////////////////////////
                VistaMenuGeneral vista = new VistaMenuGeneral();
                ControladorMenuGeneral controlador= new ControladorMenuGeneral(vista);
                vista.setVisible(true);
            }else{
                vista.metodoPruebaDeLogin("No coincide usuario");
            }
        }






}
