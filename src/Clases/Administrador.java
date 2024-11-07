package Clases;

import Enums.EstadoHabitacion;
import Enums.TipoHabitacion;
import Enums.TipoUsuario;

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
        return "Clases.Administrador{" +
                "contraseña='" + contraseña + '\'' +
                ", dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", tipoUsuario=" + tipoUsuario +
                '}';
    }

    //metodos para crear

    public String crearHabitacion(int numero, TipoHabitacion tipoHabitacion, EstadoHabitacion estadoHabitacion, GestorDeDatos<Habitacion> gestorDeDatos) {

        Habitacion habitacionExistente = gestorDeDatos.buscar(numero);
        if (habitacionExistente != null) {
            return "Error: Ya existe una habitación con ese número.";
        }


        Habitacion nuevaHabitacion = new Habitacion(numero, tipoHabitacion, estadoHabitacion);


        gestorDeDatos.agregar(numero, nuevaHabitacion);
        return "Habitación creada exitosamente: " + nuevaHabitacion.toString();
    }
}
