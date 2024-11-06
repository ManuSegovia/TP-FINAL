package Clases;

import java.time.LocalDate;
import java.util.Objects;

public class Reserva extends GestorDeDatos
{
    private int idPasajero;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int numeroHabitacion;

    public Reserva(int idPasajero,LocalDate fechaInicio,LocalDate fechaFin,int numeroHabitacion)
    {
        this.idPasajero =idPasajero;
        this.fechaInicio = fechaInicio;
        this.fechaFin=fechaFin;
        this.numeroHabitacion=numeroHabitacion;
    }

    public int getIdPasajero() {
        return idPasajero;
    }

    public void setIdPasajero(int idPasajero) {
        this.idPasajero = idPasajero;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getNumeroHabitacion() {
        return numeroHabitacion;
    }

    public void setNumeroHabitacion(int numeroHabitacion) {
        this.numeroHabitacion = numeroHabitacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Reserva reserva = (Reserva) o;
        return idPasajero == reserva.idPasajero && numeroHabitacion == reserva.numeroHabitacion && Objects.equals(fechaInicio, reserva.fechaInicio) && Objects.equals(fechaFin, reserva.fechaFin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idPasajero, fechaInicio, fechaFin, numeroHabitacion);
    }

    @Override
    public String toString() {
        return "Clases.Reserva{" +
                "idPasajero=" + idPasajero +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", numeroHabitacion=" + numeroHabitacion +
                '}';
    }
}
