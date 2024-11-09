package Clases;
import org.json.JSONObject;
import Enums.EstadoReserva;

import java.time.LocalDate;
import java.util.Objects;

public class Reserva extends GestorDeDatos
{
    private int idPasajero;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int numeroHabitacion;
    private EstadoReserva estado;

    public Reserva(int idPasajero,LocalDate fechaInicio,LocalDate fechaFin,int numeroHabitacion)
    {
        this.idPasajero =idPasajero;
        this.fechaInicio = fechaInicio;
        this.fechaFin=fechaFin;
        this.numeroHabitacion=numeroHabitacion;
        this.estado = EstadoReserva.PENDIENTE;
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

    public EstadoReserva getEstado() {
        return estado;
    }

    public void setEstado(EstadoReserva estado) {
        this.estado = estado;
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
        return  "idPasajero=" + idPasajero +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", numeroHabitacion=" + numeroHabitacion +
                ", estadoReserva=" + estado;
    }

    public String realizar_CHECKIN() {
        // Verifica si la fecha de inicio es en el pasado
        if (fechaInicio.isBefore(LocalDate.now())) {
            return "La fecha de inicio ya pasó, no se puede realizar el check-in.";
        }

        // Verifica si ya se realizó el check-in
        if (estado == EstadoReserva.CHECKIN) {
            return "Ya se ha realizado el check-in.";
        }

        // Realiza el check-in si la fecha es hoy o en el futuro
        if (fechaInicio.isEqual(LocalDate.now()) || fechaInicio.isAfter(LocalDate.now())) {
            estado = EstadoReserva.CHECKIN;
            return "El check-in se realizó correctamente.";
        }

        // Si la fecha de inicio está en el futuro, no se puede hacer check-in
        return "Aún no se puede realizar el check-in, la fecha de inicio está en el futuro.";
    }

    public String realizar_CHECKOUT() {
        // Verifica si la reserva ya está en estado CHECKOUT
        if (estado == EstadoReserva.CHECKOUT) {
            return "La reserva ya se encuentra en estado CHECKOUT.";
        }

        // Verifica si la fecha de fin ya pasó o es hoy
        if (fechaFin.isBefore(LocalDate.now()) || fechaFin.isEqual(LocalDate.now())) {
            estado = EstadoReserva.CHECKOUT;
            return "El check-out se realizó correctamente. Gracias por su estadía.";
        }

        // Si la fecha de fin está en el futuro, no se puede hacer check-out
        return "Aún no se puede realizar el check-out, la fecha de fin de la reserva no ha llegado.";
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("idPasajero", idPasajero);
        json.put("fechaInicio", fechaInicio.toString());
        json.put("fechaFin", fechaFin.toString());
        json.put("numeroHabitacion", numeroHabitacion);
        json.put("estado", estado.toString());
        return json;
    }

}
