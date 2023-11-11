package com.EquipoQueNoAceptaMasIntegrantes.Modelo.busquedas;

// import java.util.List;
import java.util.Collection;

import com.EquipoQueNoAceptaMasIntegrantes.Modelo.habitaciones.Habitacion;
import com.EquipoQueNoAceptaMasIntegrantes.Modelo.interfaces.Busqueda;
import com.EquipoQueNoAceptaMasIntegrantes.Modelo.repositorios.RepositorioHabitacion;

public class BusquedaNormal implements Busqueda {
    
    //List<Habitacion> habitaciones = RepositorioHabitacion.getInstance().getHabitaciones();
    //List<Habitacion> habitacionesNormales;

    public Collection<Habitacion> buscar() {
        return RepositorioHabitacion.getInstance().getNormal();
    }
}
