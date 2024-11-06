import java.util.ArrayList;
import java.util.List;
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
        return "Habitacion{" +
                "numero=" + numero +
                ", tipoHabitacion=" + tipoHabitacion +
                ", estadoHabitacion=" + estadoHabitacion +
                '}';
    }

    //reservar habitacion lo hace HOTEL

    public String cambiarEstadoHabitacion(Habitacion habitacion)
    {
        habitacion.setEstadoHabitacion(EstadoHabitacion.OCUPADA);
        return "Habitacion reservada";
    }

    //cambiar estado, asignar pasajero
}
