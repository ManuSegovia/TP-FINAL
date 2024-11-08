package Clases;

import Enums.EstadoHabitacion;
import Enums.TipoHabitacion;

import java.util.Objects;

public class Habitacion
{
    private int numero;
    private TipoHabitacion tipoHabitacion;//Creo que esta bien hacerlo enum
    private EstadoHabitacion estadoHabitacion;

    public Habitacion(int numero,TipoHabitacion tipoHabitacion,EstadoHabitacion estadoHabitacion)
    {
        this.numero=numero;
        this.tipoHabitacion=tipoHabitacion;
        this.estadoHabitacion=estadoHabitacion;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public TipoHabitacion getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(TipoHabitacion tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public EstadoHabitacion getEstadoHabitacion() {
        return estadoHabitacion;
    }

    public void setEstadoHabitacion(EstadoHabitacion estadoHabitacion) {
        this.estadoHabitacion = estadoHabitacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Habitacion that = (Habitacion) o;
        return numero == that.numero;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }

    @Override
    public String toString() {
        return "numero=" + numero +
                ", tipoHabitacion=" + tipoHabitacion +
                ", estadoHabitacion=" + estadoHabitacion;
    }

    //reservar habitacion lo hace HOTEL

    public String cambiarEstadoHabitacion()
    {
        if (estadoHabitacion == EstadoHabitacion.DISPONIBLE)
        {
            setEstadoHabitacion(EstadoHabitacion.OCUPADA);
            return "ocupada";
        }
        else
        {
            if (estadoHabitacion == EstadoHabitacion.OCUPADA)
            {
                setEstadoHabitacion(EstadoHabitacion.DISPONIBLE);
                return "disponible";
            }
            else
            {
                return "No se puede cambiar el estado de esta habitacion debido a que no se encuentra disponible por montenimiento u otro motivo";
            }
        }
    }


    //cambiar estado, asignar pasajero
}
