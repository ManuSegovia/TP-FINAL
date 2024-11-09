package Clases;

import java.util.Scanner;

public class Menu {

    // Método para pedir el DNI al usuario
    public static String pedirDniInicio() {
        Scanner scanner = new Scanner(System.in);

        // Mostrar el cartel
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║        B I E N V E N I D O   a         ║");
        System.out.println("║        H O T E L   V A L L E S         ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║                                        ║");
        System.out.println("║     Ingrese su DNI para comenzar       ║");
        System.out.println("║                                        ║");
        System.out.print("╠════════════════════════════════════════╣\n");
        System.out.print("║  DNI: ");

        String dni = scanner.nextLine(); // Lee el DNI del usuario

        // Regresar el DNI ingresado
        System.out.println("╚════════════════════════════════════════╝");

        return dni; // Retorna el DNI ingresado como String
    }
    public static String pedirTexto ()
    {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static String pedirDni() {
        Scanner scanner = new Scanner(System.in);
        String dni = scanner.nextLine(); // Lee el DNI del usuario
        return dni; // Retorna el DNI ingresado como String
    }

    // Método para pedir la contraseña al usuario
    public static String pedirContraseña() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Por favor, ingrese su contraseña: ");
        return scanner.nextLine(); // Retorna la contraseña como String
    }


    public static int mostrarMenuAdministrador() {
        Scanner scanner = new Scanner(System.in);
        int opcion = -1; // Inicializamos la opción con un valor no válido

        // Bucle para mostrar el menú continuamente hasta que el usuario elija cerrar sesión
        while (opcion != 0) {
            // Imprimir el menú dentro del bucle
            System.out.println("Seleccione la función a realizar:");
            System.out.println("1- Crear habitación");
            System.out.println("2- Listar habitaciones");
            System.out.println("3- Listar Pasajeros");
            System.out.println("4- Listar Conserjes");
            System.out.println("5- Modificar Pasajero");
            System.out.println("6- Modificar Concerje");
            System.out.println("7- Modificar datos propios");
            System.out.println("0- Cerrar sesión");

            System.out.print("Ingrese el número de la opción: ");

            // Verificamos si el usuario ingresa un número entero válido
            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();  // Leemos la opción
                scanner.nextLine();  // Limpiar el buffer para evitar problemas con entradas futuras

                switch (opcion) {
                    case 1:
                        return 1; // Opción para crear habitación
                    case 2:
                        return 2; // Opción para listar habitaciones
                    case 3:
                        return 3; // Opción para listar pasajeros
                    case 4:
                        return 4; // Opción para listar conserjes
                    case 5:
                        return 5; // Opción para modificar pasajero
                    case 6:
                        return 6; // Opción para modificar conserje
                    case 7:
                        return 7; // Opción para modificar datos propios
                    case 0:
                        return 0; // Opción para cerrar sesión
                    default:
                        System.out.println("Opción no válida. Intente nuevamente.");
                }
            } else {
                System.out.println("Por favor, ingrese un número válido.");
                scanner.nextLine(); // Limpiar el buffer si no es un número entero
            }
        }

        return -1; // Retornamos -1 si el bucle termina inesperadamente
    }
    public static int menuModificarPasajero() {
        Scanner scanner = new Scanner(System.in);
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("\nSeleccione el atributo que desea modificar:");
            System.out.println("1. Nombre");
            System.out.println("2. Apellido");
            System.out.println("3. DNI");
            System.out.println("4. Origen");
            System.out.println("5. Domicilio de Origen");
            System.out.println("0. Salir");

            // Leer opción
            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine();  // Limpiar el buffer de entrada

                switch (opcion) {
                    case 1:
                        return 1; // Opción para modificar nombre
                    case 2:
                        return 2; // Opción para modificar apellido
                    case 3:
                        return 3; // Opción para modificar dni
                    case 4:
                        return 4; // Opción para modificar origin
                    case 5:
                        return 5; // Opción para modificar domicilio origin
                    case 0:
                        return 0; // Opcion para salir de esta funcion
                    default:
                        System.out.println("Opción no válida, intente nuevamente.");
                }
            } else {
                System.out.println("Por favor, ingrese un número válido.");
                scanner.nextLine(); // Limpiar buffer
            }
        }
        return -1;
    }

    public static int menuModificarConserje() {
        Scanner scanner = new Scanner(System.in);
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("\nSeleccione el atributo que desea modificar:");
            System.out.println("1. Nombre");
            System.out.println("2. DNI");
            System.out.println("0. Salir");

            // Leer opción
            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine();  // Limpiar el buffer de entrada

                switch (opcion) {
                    case 1:
                        return 1; // Opción para modificar nombre
                    case 2:
                        return 2; // Opción para modificar dni
                    case 0:
                        return 0; // Opción para salir de esta función
                    default:
                        System.out.println("Opción no válida, intente nuevamente.");
                }
            } else {
                System.out.println("Por favor, ingrese un número válido.");
                scanner.nextLine(); // Limpiar buffer
            }
        }
        return -1;
    }

    public static int menuModificarAdministrador() {
        Scanner scanner = new Scanner(System.in);
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("\nSeleccione el atributo que desea modificar:");
            System.out.println("1. Nombre");
            System.out.println("2. DNI");
            System.out.println("3. Contraseña");
            System.out.println("0. Salir");

            // Leer opción
            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine();  // Limpiar el buffer de entrada

                switch (opcion) {
                    case 1:
                        return 1; // Opción para modificar nombre
                    case 2:
                        return 2; // Opción para modificar DNI
                    case 3:
                        return 3; // Opción para modificar contraseña
                    case 0:
                        return 0; // Opción para salir de esta función
                    default:
                        System.out.println("Opción no válida, intente nuevamente.");
                }
            } else {
                System.out.println("Por favor, ingrese un número válido.");
                scanner.nextLine(); // Limpiar buffer
            }
        }
        return -1;
    }




}
