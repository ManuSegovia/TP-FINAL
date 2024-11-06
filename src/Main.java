import java.time.LocalDate;
import java.util.Scanner;

public class Main
{
    //saber usar hash y set y mapas para el parcial
    //interfaces de ocupable
    public static void main(String[] args)
    {
        //admin
        //tendria que poder hacer todo en el programa
        //backup de la informacion
        //creacion de otros usuairos
        //asignacion de permisos, etc

        //conserje/recepcionista
        //realiza los check-in check-out
        //las reservas, etc

        //pasajero (NO)
        //solicitar reservas
        //o realizar consumos en las habitaciones ya ocupadas
        //ver habitaciones ya disponibles, etc

        Scanner scanner=new Scanner(System.in);
        int opcion;

        do
        {
            System.out.println("---MENU---");
            System.out.println("1. Ingresar");
            System.out.println("0. Salir");

            opcion=scanner.nextInt();

            switch(opcion)
            {
                case 1:
                    //iniciar sesion
                    break;
                case 2:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion incorrecta...");
            }

        }while(opcion!=0);

        scanner.close();
    }

    Pasajero lola=new Pasajero("lola","perez","44860140","jdkjad","ksdsaas");
    Habitacion habitacion= new Habitacion(1,TipoHabitacion.SIMPLE,EstadoHabitacion.DISPONIBLE);
    Reserva reserva= new Reserva(lola,LocalDate.of(2024,10,24),LocalDate.of(2025,10,24),habitacion);


//CONSULTAS
    //consultar listado de de habitaciones actualmente ocupadas

}