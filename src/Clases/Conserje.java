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
        return "Dni:'" + dni + '\'' +
                ", Nombre:'" + nombre + '\'' +
                ", TipoUsuario:" + tipoUsuario;
    }

    // Método toJSON para convertir a JSON
    @Override
    public JSONObject toJSON() {
        JSONObject jsonObject = super.toJSON(); // Obtiene el JSON de la clase base Usuario
        return jsonObject; // Devuelve el JSON del objeto
    }

}
