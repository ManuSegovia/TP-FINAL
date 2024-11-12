package Clases;

import Enums.EstadoHabitacion;
import Enums.TipoHabitacion;
import Interfaces.JSONable;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Objects;

public class Habitacion implements JSONable
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
        return "Numero:" + numero +'\n'+
                "TipoHabitacion:" + tipoHabitacion +'\n' +
                "EstadoHabitacion:" + estadoHabitacion;
    }


    // Método para convertir una Habitacion a JSON
    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("numero", numero);
        jsonObject.put("tipoHabitacion", tipoHabitacion.toString()); // Convertir el TipoHabitacion a su nombre
        jsonObject.put("estadoHabitacion", estadoHabitacion.toString()); // Convertir el EstadoHabitacion a su nombre
        return jsonObject;
    }

    // Método para almacenar varias habitaciones en formato JSON
    public static JSONObject habitacionesToJSON(Habitacion... habitaciones) {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        // Agregar cada habitación al JSONArray
        for (Habitacion habitacion : habitaciones) {
            jsonArray.put(habitacion.toJSON()); // Agregar la habitación convertida a JSON
        }

        jsonObject.put("habitaciones", jsonArray); // Colocar el array dentro del objeto JSON
        return jsonObject;
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
