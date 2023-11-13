package com.EquipoQueNoAceptaMasIntegrantes.Controladores;

import com.EquipoQueNoAceptaMasIntegrantes.Modelo.habitacionesYPaquetes.Habitacion;
import com.EquipoQueNoAceptaMasIntegrantes.Modelo.repositorios.RepositorioHabitacion;
import com.EquipoQueNoAceptaMasIntegrantes.Modelo.repositorios.RepositorioUsuario;
import com.EquipoQueNoAceptaMasIntegrantes.RecepcionHotel;
import com.EquipoQueNoAceptaMasIntegrantes.Vista.VistaDecoraciones;
import com.EquipoQueNoAceptaMasIntegrantes.Vista.VistaHabitaciones;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ControladorHabitaciones implements ActionListener {
    private VistaHabitaciones vista;
    private RepositorioHabitacion modelo;
    private TipoHabitacion tipoHabitacionSeleccionado;
    private Habitacion habitacionSeleccionada;

    public ControladorHabitaciones(VistaHabitaciones vista, RepositorioHabitacion modelo){
        this.vista=vista;
        this.modelo=modelo;
        this.vista.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String botonPresionado=actionEvent.getActionCommand();

        if(botonPresionado.equals("Confirmar")) {
            botonConfirmar();
        }
        else
            botonConfirmarNumHabitacion();

    }
    private void botonConfirmar(){
        int personas=vista.getPersonas();
        int noches = vista.getNoches();
        if(personas <=0 || personas >=11 || noches <=0 || noches >=11)
            vista.setTextError("Ingresa numero mayores que 0 y menores a 11");
        else {
            vista.setTextError("");
            if (personas <= 2) {
                tipoHabitacionSeleccionado = TipoHabitacion.NORMAL;
                vista.setTextHabitaciones(modelo.mostrarNombre(modelo.getNormal()));
            } else if (personas <= 5) {
                tipoHabitacionSeleccionado = TipoHabitacion.SUITE;
                vista.setTextHabitaciones(modelo.mostrarNombre(modelo.getSuite()));
            } else if (personas <= 8) {
                tipoHabitacionSeleccionado = TipoHabitacion.GRANDSUITE;
                vista.setTextHabitaciones(modelo.mostrarNombre(modelo.getGrandSuite()));
            }

            vista.activarBotones();
        }
    }
    private void botonConfirmarNumHabitacion(){
        int numHabitacion= vista.getNumHabitacion();
        if (esNumeroHabitacionValido(numHabitacion, tipoHabitacionSeleccionado)){

            Habitacion habitacionSeleccionada = modelo.find((long) numHabitacion);
            VistaDecoraciones vista=new VistaDecoraciones();
            ControladorDecoradores controladorDecoradores=new ControladorDecoradores(vista);
            this.vista.setVisible(false);
            vista.setVisible(true);
           // vista.setText(ControladorIdioma.msg.getProperty("msg.resumenHabitacion"));
            vista.setText(habitacionSeleccionada.toString());
        }else
            vista.setTextErrorNumHab("Ingresa un numero correcto");
    }

    public enum TipoHabitacion {
        NORMAL, SUITE, GRANDSUITE
    }

    private boolean esNumeroHabitacionValido(int numeroHabitacion, TipoHabitacion tipo) {
        switch (tipo) {
            case NORMAL:
                return numeroHabitacion >= 101 && numeroHabitacion <= 104;
            case SUITE:
                return numeroHabitacion >= 201 && numeroHabitacion <= 202;
            case GRANDSUITE:
                return numeroHabitacion >= 301 && numeroHabitacion <= 302;
            default:
                return false; // En caso de que el tipo no sea reconocido
        }
    }

    // inesperado

    // Ahora que tenemos un número válido, continuamos con el flujo del programa
    // Muestra las habitaciones disponibles basadas en el número de personas


}
