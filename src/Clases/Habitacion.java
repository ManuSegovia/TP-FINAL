package Clases;

import Enums.EstadoHabitacion;
import Enums.TipoHabitacion;
import org.json.JSONObject;

public class Habitacion
{
    private int numero;
    private TipoHabitacion tipoHabitacion;//Creo que esta bien hacerlo enum
    private EstadoHabitacion estadoHabitacion;


    public Habitacion(int numero, TipoHabitacion tipoHabitacion, EstadoHabitacion estadoHabitacion) {
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
    public String toString() {
        return "numero=" + numero +
                ", tipoHabitacion=" + tipoHabitacion +
                ", estadoHabitacion=" + estadoHabitacion +
                '}';
    }

    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("numero", this.numero);
        jsonObject.put("tipoHabitacion", this.tipoHabitacion.toString());
        jsonObject.put("estadoHabitacion", this.estadoHabitacion.toString());
        return jsonObject;
    }

    public static Habitacion fromJSON(JSONObject json) {
        int numero = json.getInt("numero");
        TipoHabitacion tipoHabitacion = TipoHabitacion.valueOf(json.getString("tipoHabitacion"));
        EstadoHabitacion estadoHabitacion = EstadoHabitacion.valueOf(json.getString("estadoHabitacion"));
        return new Habitacion(numero, tipoHabitacion, estadoHabitacion);
    }


    //cambiar estado, asignar pasajero
}
