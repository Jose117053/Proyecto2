package com.EquipoQueNoAceptaMasIntegrantes.Controladores;

import com.EquipoQueNoAceptaMasIntegrantes.Controlador.util.Mensajes;
import com.EquipoQueNoAceptaMasIntegrantes.Modelo.repositorios.RepositorioHabitacion;
import com.EquipoQueNoAceptaMasIntegrantes.Modelo.repositorios.RepositorioUsuario;
import com.EquipoQueNoAceptaMasIntegrantes.Vista.VistaHabitaciones;
import com.EquipoQueNoAceptaMasIntegrantes.Vista.VistaLogin;
import com.EquipoQueNoAceptaMasIntegrantes.Vista.VistaMenuGeneral;
import com.EquipoQueNoAceptaMasIntegrantes.Vista.VistaOfertas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Timer;

public class ControladorMenuGeneral {
    VistaMenuGeneral vista;

    public ControladorMenuGeneral(VistaMenuGeneral vista){
        this.vista=vista;
        modelarAcciones();
        accionNotificarOfertas();
    }
    public void modelarAcciones(){
        vista.addActionListenerEspecifico(vista.getBotonHabitaciones(), accionVerHabitaciones());
        vista.addActionListenerEspecifico(vista.getBotonOfertas(), accionVerOfertas());
    }
    public ActionListener accionVerHabitaciones(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                vista.setVisible(false);
                VistaHabitaciones vistaHabitaciones=new VistaHabitaciones();
                RepositorioHabitacion repositorioHabitacion=new RepositorioHabitacion();
                ControladorHabitaciones controladorHabitaciones=new ControladorHabitaciones(vistaHabitaciones, repositorioHabitacion);
                vistaHabitaciones.setVisible(true);
            }
        };
    }

    public ActionListener accionVerOfertas(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                vista.setVisible(false);
                VistaOfertas vistaOfertas=new VistaOfertas();
                vistaOfertas.setVisible(true);
                ControladorOfertas controladorOfertas=new ControladorOfertas(vistaOfertas);
                vistaOfertas.imprimirOfertas();
            }
        };
    }

    public void accionNotificarOfertas(){
                try {
                    ControladorInicio.generadorOfertas.simularCreadorOferta(ControladorIdioma.codigoPais, vista);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
    }
}
