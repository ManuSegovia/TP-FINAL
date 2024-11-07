package Clases;

import Enums.EstadoHabitacion;
import Enums.TipoHabitacion;
import Exceptions.HabitacionInexistenteException;
import Exceptions.PasajeroInexistenteException;

import java.time.LocalDate;
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

// Cuestiones relacionadas a las reservas  -------------------------------------------------------------------------------------------
    public String listarHabitacionesActualmenteOcupadas()
    {
        return reservas.listarHabitacionesOcupadas();
    }

    public String listarHabitacionesDisponibles()
    {
        return reservas.listarHabitacionesDisponibles();
    }

    public String listarHabitacionesOcupadasMotivo(EstadoHabitacion motivo)
    {
        return reservas.listarHabitacionesOcupadasMotivo(motivo);
    }

    //ocupar una habitación en un período determinado (consultando la ocupación y las reservas).

    public String generarReserva(int numeroHabtacion, int idPasajero, LocalDate fechaReserva, LocalDate fechaFinReserva)throws HabitacionInexistenteException, PasajeroInexistenteException
    {
        if(habitaciones.buscar(numeroHabtacion)==null)
        {
            throw new HabitacionInexistenteException();
        }
        if(pasajeros.buscar(idPasajero)==null)
        {
            throw new PasajeroInexistenteException();
        }
        Reserva nueva = new Reserva(idPasajero,fechaReserva,fechaFinReserva,numeroHabtacion);
        String rtdo = reservas.agregar(numeroHabtacion, nueva);
        return rtdo;
    }
    public String eliminarReserva(int numeroHabtacion, int idPasajero, LocalDate fechaReserva, LocalDate fechaFinReserva)throws HabitacionInexistenteException, PasajeroInexistenteException
    {
        if(habitaciones.buscar(numeroHabtacion)==null)
        {
            throw new HabitacionInexistenteException();
        }
        if(pasajeros.buscar(idPasajero)==null)
        {
            throw new PasajeroInexistenteException();
        }
        String rtdo = reservas.eliminar(numeroHabtacion, idPasajero, fechaReserva, fechaFinReserva);
        return rtdo;
    }
    //---------------------------------------------------------------------------------------------------------------------------------
    // Cuestiones relacionandas con las habitaciones (Parte concerje) -----------------------------------------------------------------
    public String listarHabitaciones()
    {
        return habitaciones.listar();
    }
    // Cuestiones relacionandas con las habitaciones (Parte admin) -----------------------------------------------------------------

    public String crearHabitacionNueva (Administrador admin, int numero, TipoHabitacion tipoHabitacion, EstadoHabitacion estadoHabitacion)
    {
        if (habitaciones.buscarBoolean(numero))git 
        {
            return "La habitacion ya existe";
        }
        else {
            Habitacion HabitacionNueva = admin.crearHabitacion(numero, tipoHabitacion, estadoHabitacion);
            habitaciones.agregar(numero, HabitacionNueva);
            return "Se creo correctamente la habitacion " + numero;
        }
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
        return "Clases.Hotel{" +
                "nombre='" + nombre + '\'' +
                ", habitaciones=" + habitaciones +
                ", pasajeros=" + pasajeros +
                ", reservas=" + reservas +
                '}';
    }
}
