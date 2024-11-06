import java.time.LocalDate;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

public class Hotel
{
    //nuevo
    private String nombre;
    private GestorDeDatos<Habitacion>habitaciones;
    private GestorDeDatos<Pasajero>pasajeros;
    private GestorDeReservas<Reserva> reservas;

    public Hotel(String nombre)
    {
        this.nombre=nombre;
        this.habitaciones=new GestorDeDatos<Habitacion>();
        this.pasajeros=new GestorDeDatos<Pasajero>();
        this.reservas=new GestorDeReservas<Reserva>();
    }

    public String listarHabitaciones()
    {
        return habitaciones.listar();
    }

    public String listarHabitacionesActualmenteOcupadas()
    {
        return reservas.listarHabitacionesOcupadas();
    }

    public String listarHbitacionesDisponibles()
    {
        return reservas.listarHabitacionesDisponibles();
    }

    public String listarHabitacionesOcupadasMotivo(EstadoHabitacion motivo)
    {
        return reservas.listarHabitacionesOcupadasMotivo(motivo);
    }

    //ocupar una habitación en un período determinado (consultando la ocupación y las reservas).

    public void generaeReserva(int numeroHabtacion, int idPasajero, LocalDate fechaReserva, LocalDate fechaFinReserva)throws HabitacionInexistenteException,PasajeroInexistenteException
    {
        if(habitaciones.buscar(numeroHabtacion)==null)
        {
            throw new HabitacionInexistenteException();
        }
        if(pasajeros.buscar(idPasajero)==null)
        {
            throw new PasajeroInexistenteException();
        }
        //verificar si se puede reservar
        Reserva nueva = new Reserva(idPasajero,fechaReserva,fechaFinReserva,numeroHabtacion);
        reservas.agregar(numeroHabtacion,nueva);
    }





    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public GestorDeDatos<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(GestorDeDatos<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }

    public GestorDeDatos<Pasajero> getPasajeros() {
        return pasajeros;
    }

    public void setPasajeros(GestorDeDatos<Pasajero> pasajeros) {
        this.pasajeros = pasajeros;
    }

    public GestorDeReservas<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(GestorDeReservas<Reserva> reservas) {
        this.reservas = reservas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotel hotel = (Hotel) o;
        return Objects.equals(nombre, hotel.nombre) && Objects.equals(habitaciones, hotel.habitaciones) && Objects.equals(pasajeros, hotel.pasajeros) && Objects.equals(reservas, hotel.reservas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, habitaciones, pasajeros, reservas);
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "nombre='" + nombre + '\'' +
                ", habitaciones=" + habitaciones +
                ", pasajeros=" + pasajeros +
                ", reservas=" + reservas +
                '}';
    }
}
