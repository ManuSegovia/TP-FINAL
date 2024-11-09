package Clases;

import Enums.TipoUsuario;

public class Conserje extends Usuario {
    public Conserje(String dni, String nombre) {
        super(dni, nombre, TipoUsuario.CONSERJE);
    }

    @Override
    public String toString() {
        return "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", tipoUsuario=" + tipoUsuario;
    }

    //checkiout, checkin
}
