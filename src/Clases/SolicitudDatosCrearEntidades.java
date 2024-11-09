package Clases;

import Enums.EstadoHabitacion;
import Enums.TipoHabitacion;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SolicitudDatosCrearEntidades {

    public static Pasajero crearPasajero(Hotel mihotel) {
        Scanner scanner = new Scanner(System.in);
        Pasajero nuevoPasajero = null;

        // Continuar pidiendo los datos hasta que se cree un pasajero con un DNI único
        while (nuevoPasajero == null) {
            // Solicitar datos al conserje
            System.out.println("Ingrese el nombre del pasajero:");
            String nombre = scanner.nextLine();

            System.out.println("Ingrese el apellido del pasajero:");
            String apellido = scanner.nextLine();

            System.out.println("Ingrese el DNI del pasajero:");
            String dni = scanner.nextLine();

            // Comprobar si el pasajero ya existe en el sistema
            boolean pasajeroExistente = mihotel.getPasajeros().getElementos().values()
                    .stream()
                    .anyMatch(pasajero -> pasajero.getDni().equals(dni));

            if (pasajeroExistente) {
                System.out.println("El pasajero con DNI " + dni + " ya existe en el sistema. Por favor, ingrese un DNI diferente.");
                continue; // Volver a solicitar los datos
            }

            // Si el DNI es único, pedir más datos
            System.out.println("Ingrese el origen del pasajero:");
            String origen = scanner.nextLine();

            System.out.println("Ingrese el domicilio de origen del pasajero:");
            String domicilioOrigen = scanner.nextLine();

            // Crear un nuevo objeto Pasajero con los datos proporcionados
            nuevoPasajero = new Pasajero(nombre, apellido, dni, origen, domicilioOrigen);

            // Almacenar al pasajero en el mapa
            mihotel.getPasajeros().agregar(nuevoPasajero.getId(), nuevoPasajero);

            // Retornar el objeto Pasajero creado
            System.out.println("Pasajero creado exitosamente con el ID: " + nuevoPasajero.getId());

        }
        return nuevoPasajero;
    }


    // creacion de habitacion
    public static void crearHabitacion(Hotel hotel) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Ingrese el numero de la habitacion");
            int numero = scanner.nextInt();

            System.out.println("Ingrese el tipo de habitacion");
            TipoHabitacion tipohabitacion = null;
            boolean tipoValido = false;
            while (!tipoValido) {
                System.out.println("1- SUITE");
                System.out.println("2- DOBLE");
                System.out.println("3- SIMPLE");
                int tipo = scanner.nextInt();
                switch (tipo) {
                    case 1:
                        tipohabitacion = TipoHabitacion.SUITE;
                        tipoValido = true;
                        break;
                    case 2:
                        tipohabitacion = TipoHabitacion.DOBLE;
                        tipoValido = true;
                        break;
                    case 3:
                        tipohabitacion = TipoHabitacion.SIMPLE;
                        tipoValido = true;
                        break;
                    default:
                        System.out.println("Tipo de habitacion invalido. Intente nuevamente.");
                }
            }

            EstadoHabitacion estado = null;
            boolean estadoValido = false;
            while (!estadoValido) {
                System.out.println("1- DISPONIBLE");
                System.out.println("2- OCUPADA");
                System.out.println("3- MANTENIMIENTO");
                System.out.println("4- OTRO");
                int estadoSeleccionado = scanner.nextInt();
                switch (estadoSeleccionado) {
                    case 1:
                        estado = EstadoHabitacion.DISPONIBLE;
                        estadoValido = true;
                        break;
                    case 2:
                        estado = EstadoHabitacion.OCUPADA;
                        estadoValido = true;
                        break;
                    case 3:
                        estado = EstadoHabitacion.MANTENIMIENTO;
                        estadoValido = true;
                        break;
                    case 4:
                        estado = EstadoHabitacion.OTRO;
                        estadoValido = true;
                        break;
                    default:
                        System.out.println("Estado de habitacion invalido. Intente nuevamente.");
                }
            }

            System.out.println(hotel.crearHabitacionNueva(numero, tipohabitacion, estado));  // Agregar la nueva habitación al hotel

        } catch (InputMismatchException e) {
            System.out.println("Error al ingresar alguno de los datos: " + e.getMessage());
        }
    }

}
