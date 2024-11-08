package Clases;

import java.util.Objects;

public class Pasajero
{
    private int id;
    private static int cont=1;
    private String nombre;
    private String apellido;
    private String dni;
    private String origen;
    private String domicilioOrigen;

    public Pasajero(String nombre, String apellido, String dni, String origen, String domicilioOrigen) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.origen = origen;
        this.domicilioOrigen = domicilioOrigen;
        this.id=cont;
        cont++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getCont() {
        return cont;
    }

    public static void setCont(int cont) {
        Pasajero.cont = cont;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDomicilioOrigen() {
        return domicilioOrigen;
    }

    public void setDomicilioOrigen(String domicilioOrigen) {
        this.domicilioOrigen = domicilioOrigen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pasajero pasajero = (Pasajero) o;
        return id == pasajero.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", origen='" + origen + '\'' +
                ", domicilioOrigen='" + domicilioOrigen;
    }
}
