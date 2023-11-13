package com.EquipoQueNoAceptaMasIntegrantes.Controladores;

import com.EquipoQueNoAceptaMasIntegrantes.Controlador.util.Mensajes;
import com.EquipoQueNoAceptaMasIntegrantes.Modelo.repositorios.RepositorioHabitacion;
import com.EquipoQueNoAceptaMasIntegrantes.Modelo.repositorios.RepositorioUsuario;
import com.EquipoQueNoAceptaMasIntegrantes.Vista.VistaHabitaciones;
import com.EquipoQueNoAceptaMasIntegrantes.Vista.VistaLogin;
import com.EquipoQueNoAceptaMasIntegrantes.Vista.VistaMenuGeneral;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ControladorMenuGeneral implements ActionListener {
    VistaMenuGeneral vista;

    public ControladorMenuGeneral(VistaMenuGeneral vista){
        this.vista=vista;
        this.vista.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String idioma= actionEvent.getActionCommand();
        Object  vista=null;
        Object  modelo=null;
        Object controlador=null;

        if (idioma.equals("Habitaciones")) {
            vista=new VistaHabitaciones();
            modelo=new RepositorioHabitacion();
            controlador=new ControladorHabitaciones((VistaHabitaciones) vista, (RepositorioHabitacion) modelo);
            this.vista.setVisible(false);
            ((VistaHabitaciones) vista).setVisible(true);
        }
        else if(idioma.equals("Ofertas")){

            System.out.println("hacer clases de vista de oferta");
        }



        /*
        VistaHabitaciones vista = new VistaHabitaciones();
        vista.setSize(900,720);
        vista.setLocation(0,0);
        panel.removeAll();
        panel.add(vista, BorderLayout.CENTER);
        panel.revalidate();
        panel.repaint();

         */
    }
}
