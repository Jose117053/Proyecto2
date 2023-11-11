package com.EquipoQueNoAceptaMasIntegrantes.Controlador.util;

import com.EquipoQueNoAceptaMasIntegrantes.Modelo.objetos.Usuario;
import com.EquipoQueNoAceptaMasIntegrantes.Vista.AhoraSi;

public class ControladorBIenvenida {
    private Usuario elUsuario;
    private AhoraSi laVista;

    public ControladorBIenvenida(Usuario elUsuario, AhoraSi laVista){
        this.elUsuario=elUsuario;
        this.laVista=laVista;

    }
}
