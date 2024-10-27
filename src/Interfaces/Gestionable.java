package Interfaces;

import Clases.Habitacion;
import Usuarios.Pasajero;

import java.time.LocalDate;

public interface Gestionable
{
    void realizarCheckIn(Pasajero pasajero, LocalDate fechaInicio, LocalDate fechaFin, Habitacion habitacion);
    void realizarCheckOut(Habitacion habitacion);
    boolean estaHabitacionDisponible(Habitacion habitacion, LocalDate fechaInicio, LocalDate fechaFin);
    void realizarReserva(Pasajero pasajero, LocalDate fechaInicio, LocalDate fechaFin, Habitacion habitacion);
}
