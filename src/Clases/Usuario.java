package Clases;

import Enums.TipoUsuario;

import java.util.Objects;

public abstract class Usuario
{
    protected int id;
    protected String dni;
    protected String nombre;
    protected TipoUsuario tipoUsuario;
    private static int cont = 1;

    public Usuario(String dni,String nombre,TipoUsuario tipoUsuario)
    {
        this.id = cont;
        this.dni=dni;
        this.nombre=nombre;
        this.tipoUsuario=tipoUsuario;
        cont++;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(dni, usuario.dni) && Objects.equals(nombre, usuario.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni, nombre);
    }

    @Override
    public String toString() {
        return "Clases.Usuario{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }


}
