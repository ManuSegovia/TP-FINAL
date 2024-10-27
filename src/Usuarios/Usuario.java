package Usuarios;

import Enums.TipoUsuario;
import org.json.JSONObject;

public abstract class Usuario
{
    protected String dni;
    protected String nombre;
    protected TipoUsuario tipoUsuario;

    public Usuario(String dni, String nombre, TipoUsuario tipoUsuario)
    {
        this.dni=dni;
        this.nombre=nombre;
        this.tipoUsuario=tipoUsuario;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public String toString() {
        return  "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
    public abstract JSONObject toJson();

}
