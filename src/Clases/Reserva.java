package Clases;

import Usuarios.Pasajero;
import org.json.JSONObject;

import java.time.LocalDate;

public class Reserva extends GestorDeDatos
{
    private Pasajero pasajeroActual;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    Habitacion habitacion;

    public Reserva(Pasajero pasajero, LocalDate fechaInicio, LocalDate fechaFin, Habitacion habitacion)
    {
        this.pasajeroActual =pasajero;
        this.fechaInicio=fechaInicio;
        this.fechaFin=fechaFin;
        this.habitacion=habitacion;
    }

    public Pasajero getPasajeroActual() {
        return pasajeroActual;
    }

    public void setPasajeroActual(Pasajero pasajeroActual) {
        this.pasajeroActual = pasajeroActual;
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

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }


    @Override
    public String toString() {
        return "pasajeroActual=" + pasajeroActual +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", habitacion=" + habitacion +
                '}';
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("pasajero", pasajeroActual.toJSON());
        json.put("fechaInicio", fechaInicio.toString());
        json.put("fechaFin", fechaFin.toString());
        json.put("habitacion", habitacion.toJSON());
        return json;
    }

    public static Reserva fromJSON(JSONObject jsonObject) {
        Pasajero pasajero = Pasajero.fromJson(jsonObject.getJSONObject("pasajero"));
        LocalDate fechaInicio = LocalDate.parse(jsonObject.getString("fechaInicio"));
        LocalDate fechaFin = LocalDate.parse(jsonObject.getString("fechaFin"));
        Habitacion habitacion = Habitacion.fromJSON(jsonObject.getJSONObject("habitacion"));
        return new Reserva(pasajero, fechaInicio, fechaFin, habitacion);
    }
}
