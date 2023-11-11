package com.EquipoQueNoAceptaMasIntegrantes.Modelo.busquedas;

import java.util.Collection;

import com.EquipoQueNoAceptaMasIntegrantes.Modelo.habitaciones.Habitacion;
import com.EquipoQueNoAceptaMasIntegrantes.Modelo.interfaces.Busqueda;
import com.EquipoQueNoAceptaMasIntegrantes.Modelo.repositorios.RepositorioHabitacion;

public class BusquedaSuite implements Busqueda {
    
    public Collection<Habitacion> buscar() {
        return RepositorioHabitacion.getInstance().getSuite();
    }
}
