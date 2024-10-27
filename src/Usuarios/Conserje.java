package Usuarios;

import Enums.TipoUsuario;
import org.json.JSONObject;

public class Conserje extends Usuario{

    public Conserje(String dni, String nombre) {
        super(dni, nombre, TipoUsuario.CONSERJE);
    }

    @Override
    public String toString() {
        return "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", tipoUsuario=" + tipoUsuario +
                '}';
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("dni", dni);
        json.put("nombre", nombre);
        json.put("tipoUsuario", tipoUsuario.toString());
        return json;
    }

    public static Conserje fromJson(JSONObject json) {
        String dni = json.getString("dni");
        String nombre = json.getString("nombre");

        return new Conserje(dni, nombre);
    }
}

