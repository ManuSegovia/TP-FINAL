package Clases;

public class Menu
{
    public static void Menu_Administrador ()
    {
        System.out.println("=== Menú del Administrador ===");
        System.out.println("1. Crear usuario");
        System.out.println("2. Realizar backup de la información");
        System.out.println("3. Asignar permisos a usuarios");
        System.out.println("4. Listar usuarios");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción: ");
    }
    public static void Menu_Concerje ()
    {
        System.out.println("=== Menú del Conserje ===");
        System.out.println("1. Realizar Check-in");
        System.out.println("2. Realizar Check-out");
        System.out.println("3. Realizar Reserva");
        System.out.println("4. Listar habitaciones ocupadas");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción: ");
    }
}
