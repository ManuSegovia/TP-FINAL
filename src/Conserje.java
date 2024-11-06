public class Conserje extends Usuario
{
    public Conserje(String dni,String nombre)
    {
        super(dni,nombre,TipoUsuario.CONSERJE);
    }

    @Override
    public String toString() {
        return "Conserje{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", tipoUsuario=" + tipoUsuario +
                '}';
    }

    //checkiout, checkin
}
