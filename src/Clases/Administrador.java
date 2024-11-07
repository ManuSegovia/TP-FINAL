package Clases;

import Enums.EstadoHabitacion;
import Enums.TipoHabitacion;
import Enums.TipoUsuario;

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
        return "Clases.Administrador{" +
                "contraseña='" + contraseña + '\'' +
                ", dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", tipoUsuario=" + tipoUsuario +
                '}';
    }

    //metodos para crear

    public Habitacion crearHabitacion(int numero, TipoHabitacion tipoHabitacion, EstadoHabitacion estadoHabitacion) {
        // Crear la nueva habitación

        Habitacion nuevaHabitacion = new Habitacion(numero, tipoHabitacion, estadoHabitacion);

        return nuevaHabitacion;
    }
}
