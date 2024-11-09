package Clases;

import Enums.TipoUsuario;
import org.json.JSONArray;
import org.json.JSONObject;

public class Conserje extends Usuario {
    public Conserje(String dni, String nombre) {
        super(dni, nombre, TipoUsuario.CONSERJE);
    }

    @Override
    public String toString() {
        return "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", tipoUsuario=" + tipoUsuario;
    }

    // Método para convertir un Conserje a JSON
    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("dni", dni);
        jsonObject.put("nombre", nombre);
        jsonObject.put("tipoUsuario", tipoUsuario.toString());  // Convierte el TipoUsuario a su nombre
        return jsonObject;
    }

    // Método para almacenar varios conserjes en formato JSON
    public static JSONObject conserjesToJSON(Conserje... conserjes) {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        // Agregar cada conserje al JSONArray
        for (Conserje conserje : conserjes) {
            jsonArray.put(conserje.toJSON());  // Agregar el conserje convertido a JSON
        }

        jsonObject.put("conserjes", jsonArray);  // Colocar el array dentro del objeto JSON
        return jsonObject;
    }

    //checkiout, checkin
}
