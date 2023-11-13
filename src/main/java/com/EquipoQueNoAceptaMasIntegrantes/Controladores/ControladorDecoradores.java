package com.EquipoQueNoAceptaMasIntegrantes.Controladores;

import com.EquipoQueNoAceptaMasIntegrantes.Vista.VistaDecoraciones;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorDecoradores implements ActionListener {//msgcena choca con otro jlkdsf jlkñ sadfjñ ñfds
    ///////////////////
    ////////////////////
    /////////////////////
    VistaDecoraciones vista;
    public ControladorDecoradores(VistaDecoraciones vista){
        this.vista=vista;
        this.vista.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String nombreBoton=actionEvent.getActionCommand();
        System.out.println(nombreBoton);
            if(nombreBoton.equals(ControladorIdioma.msg.getProperty("msg.Cena"))){

                vista.setText("boton cena");
            }else if(nombreBoton.equals(ControladorIdioma.msg.getProperty("msg.Champagne")))
                vista.setText("boton champage");
            else if(nombreBoton.equals(ControladorIdioma.msg.getProperty("msg.Chocolates")))
                vista.setText("boton Chocolates");
            else
                vista.setText("boton flores");
    }

    //en ver que boton se presionar if(string .equals"cena") debe ir en el
    //idioma correspondiente creo(asi que seria stringDelBoton.getSource.equals msg.cena
    //es msg.decoradores descomponerlo en "cena" "flores" y los otros
}
