package com.EquipoQueNoAceptaMasIntegrantes.Controladores;

import com.EquipoQueNoAceptaMasIntegrantes.Modelo.decoradores.*;
import com.EquipoQueNoAceptaMasIntegrantes.Vista.VistaDecoraciones;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ControladorDecoradores  {//msgcena choca con otro jlkdsf jlkñ sadfjñ ñfds
    ///////////////////
    ////////////////////
    /////////////////////
    VistaDecoraciones vista;
    ExtraHabitacion extra = ControladorHabitaciones.getHabitacionSeleccionada();

    public ControladorDecoradores(VistaDecoraciones vista){
        this.vista=vista;
        modelarAcciones();
    }
    public void modelarAcciones(){
        vista.addActionListenerEspecifico(vista.getBotonCena(), crearAccion(vista.getCantidadCena()));
        vista.addActionListenerEspecifico(vista.getBotonChampagne(), crearAccion(vista.getCantidadChampagne()));
        vista.addActionListenerEspecifico(vista.getBotonChocolates(), crearAccion(vista.getCantidadChocolates()));
        vista.addActionListenerEspecifico(vista.getBotonFlores(), crearAccion(vista.getCantidadFLores()));
        vista.addActionListenerEspecifico(vista.getBotonContinuar(), accionSalir());
    }
    private ActionListener crearAccion(JLabel label){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int cantidad=vista.getCantidadDecoraciones(label) + 1;
                String cadena=cantidad+"";
                vista.setTextoLabel(label, cadena);
                definirExtra(label.getName());
                try {
                    System.out.println(extra.descripcion(ControladorIdioma.codigoPais));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }
    public ActionListener accionSalir(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Boton continuar, mostrar el otro panel");
            }
        };
    }

    public void definirExtra(String labelName){
        switch (labelName){
            case "Cena":
                extra=new Cena(extra);
                break;
            case "Champagne":
                extra=new Champagne(extra);
                break;
            case "Chocolates":
                extra=new Chocolates(extra);
                break;
            case "Flores":
                extra=new Flores(extra);
                break;

        }
    }
}
