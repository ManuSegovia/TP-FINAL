import Clases.*;
import Enums.EstadoHabitacion;
import Enums.TipoHabitacion;

import java.time.LocalDate;
import java.util.Scanner;

public class Main
{
    //saber usar hash y set y mapas para el parcial
    //interfaces de ocupable
    public static void main(String[] args)
    {
        Hotel mihotel = new Hotel("Valles");
        Administrador admin = new Administrador("46277918", "Juani", "admin123");
        Conserje empleado = new Conserje("46277918", "Manu");
        System.out.println(mihotel.crearHabitacionNueva(admin, 3232, TipoHabitacion.DOBLE, EstadoHabitacion.DISPONIBLE));
        System.out.println(mihotel.crearHabitacionNueva(admin, 3233, TipoHabitacion.DOBLE, EstadoHabitacion.DISPONIBLE));
        System.out.println(mihotel.listarHabitaciones());
        System.out.println(mihotel.generarReservaConCreacionDePasajero(3232, LocalDate.of(2024, 11, 10), LocalDate.of(2024, 11, 15)));
        System.out.println(mihotel.listarPasajeros());
        System.out.println(mihotel.listarReservas());
        System.out.println(mihotel.generarReservaConCreacionDePasajero(3232, LocalDate.of(2024, 11, 16), LocalDate.of(2024, 11, 18)));
        System.out.println(mihotel.listarPasajeros());
        System.out.println(mihotel.listarReservas());
        System.out.println(mihotel.crearPasajero());
        System.out.println(mihotel.generarReserva(3233, 3, LocalDate.of(2024, 11, 18), LocalDate.of(2024, 11, 16)));

    }



//CONSULTAS
    //consultar listado de de habitaciones actualmente ocupadas

}