import java.util.Objects;

public abstract class Usuario
{
    protected String dni;
    protected String nombre;
    protected TipoUsuario tipoUsuario;

    public Usuario(String dni,String nombre,TipoUsuario tipoUsuario)
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
        return "Usuario{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }


}
