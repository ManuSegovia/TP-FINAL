package Clases;

import Enums.EstadoHabitacion;
import Enums.TipoHabitacion;
import Enums.TipoUsuario;
import org.json.JSONArray;
import org.json.JSONObject;

public class Administrador extends Usuario {
    protected String contraseña;

    public Administrador(String dni, String nombre, String contraseña) {
        super(dni, nombre, TipoUsuario.ADMINISTRADOR);
        this.contraseña = contraseña;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    @Override
    public String toString() {
        return "contraseña='" + contraseña + '\'' +
                ", dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", tipoUsuario=" + tipoUsuario;
    }


    // Método toJSON para convertir a JSON
    @Override
    public JSONObject toJSON() {
        JSONObject jsonObject = super.toJSON(); // Obtiene el JSON del método de la clase base
        jsonObject.put("contraseña", contraseña); // Añade la contraseña
        return jsonObject;
    }

    // Método para almacenar los administradores en formato JSON
    public static JSONObject administradoresToJSON(Administrador... administradores) {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        // Agregar cada administrador al JSONArray
        for (Administrador administrador : administradores) {
            jsonArray.put(administrador.toJSON());  // Agregar el administrador convertido a JSON
        }

        jsonObject.put("administradores", jsonArray);  // Colocar el array dentro del objeto JSON
        return jsonObject;
    }


    //metodos para crear habitacion

    public static Habitacion crearHabitacion(int numero, TipoHabitacion tipoHabitacion, EstadoHabitacion estadoHabitacion) {
        // Crear la nueva habitación

        Habitacion nuevaHabitacion = new Habitacion(numero, tipoHabitacion, estadoHabitacion);

        return nuevaHabitacion;
    }

    public static Conserje crearConserje (String dni, String nombre)
    {
        return new Conserje(dni, nombre);
    }

}
