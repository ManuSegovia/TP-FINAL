package Usuarios;

import Clases.Hotel;
import Enums.TipoUsuario;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class Administrador extends Usuario
{
    protected String contraseña;

    public Administrador(String dni,String nombre,String contraseña)
    {
        super(dni,nombre, TipoUsuario.ADMINISTRADOR);
        this.contraseña=contraseña;
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
                ", tipoUsuario=" + tipoUsuario +
                '}';
    }

    public void crearUsuario(Hotel hotel, String dni, String nombre, String contraseña, TipoUsuario tipo) throws IOException {
         Usuario nuevoUsuario;

        if (tipo == TipoUsuario.ADMINISTRADOR) {
            nuevoUsuario = new Administrador(dni, nombre, contraseña);
        } else if (tipo == TipoUsuario.CONSERJE) {
            nuevoUsuario = new Conserje(dni, nombre);
        } else {
            throw new IllegalArgumentException("Tipo de usuario no válido");
        }

        hotel.agregarUsuario(nuevoUsuario);
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("dni", dni);
        json.put("nombre", nombre);
        json.put("tipoUsuario", tipoUsuario.toString());
        json.put("contraseña", contraseña);
        return json;
    }

    public static Administrador fromJson(JSONObject json) {
        String dni = json.getString("dni");
        String nombre = json.getString("nombre");
        String contraseña = json.getString("contraseña");

        return new Administrador(dni, nombre, contraseña);
    }

}
