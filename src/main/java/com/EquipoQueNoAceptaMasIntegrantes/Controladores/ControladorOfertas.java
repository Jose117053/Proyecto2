package com.EquipoQueNoAceptaMasIntegrantes.Controladores;

import com.EquipoQueNoAceptaMasIntegrantes.Vista.VistaMenuGeneral;
import com.EquipoQueNoAceptaMasIntegrantes.Vista.VistaOfertas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorOfertas {
    VistaOfertas vistaOfertas;

    public ControladorOfertas(VistaOfertas vistaOfertas){
        this.vistaOfertas=vistaOfertas;
        modelarAcciones();
    }
    public void modelarAcciones(){
        vistaOfertas.addActionListenerEspecifico(vistaOfertas.getBotonRegresar(), accionRegresar());
    }

    public ActionListener accionRegresar(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                vistaOfertas.setVisible(false);
                VistaMenuGeneral vistaMenuGeneral=new VistaMenuGeneral();
                vistaMenuGeneral.setVisible(true);
                ControladorMenuGeneral controladorMenuGeneral=new ControladorMenuGeneral(vistaMenuGeneral);

            }
        };
    }
}
