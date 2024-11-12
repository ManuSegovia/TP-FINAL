package Clases;

import Enums.EstadoHabitacion;
import Enums.TipoHabitacion;
import Exceptions.HabitacionInexistenteException;
import Exceptions.PasajeroInexistenteException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SolicitudDatos {

    public static void crearPasajero(Hotel mihotel) {
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

    // Método para agregar un conserje al hotel
    public static void crearConserje(Hotel hotel) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Ingrese el DNI del conserje:");
            String dni = scanner.nextLine();

            System.out.println("Ingrese el nombre del conserje:");
            String nombre = scanner.nextLine();

            System.out.println(hotel.crearConserjeNuevo(dni, nombre));

        } catch (InputMismatchException e) {
            System.out.println("Error al ingresar alguno de los datos: " + e.getMessage());
        }
    }

    public static EstadoHabitacion obtenerEstadoHabitacion() {
        Scanner scanner = new Scanner(System.in);

        // Imprimir las opciones disponibles
        System.out.println("Seleccione el estado de la habitación:");
        System.out.println("1 - DISPONIBLE");
        System.out.println("2 - OCUPADA");
        System.out.println("3 - MANTENIMIENTO");
        System.out.println("4 - OTRO");

        int opcion = -1;
        while (opcion < 1 || opcion > 4) {
            // Verificar si el usuario ingresa una opción válida
            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer
            } else {
                System.out.println("Por favor, ingrese un número válido.");
                scanner.nextLine(); // Limpiar el buffer
            }

            // Retornar el estado de la habitación según la opción seleccionada
            switch (opcion) {
                case 1:
                    return EstadoHabitacion.DISPONIBLE;
                case 2:
                    return EstadoHabitacion.OCUPADA;
                case 3:
                    return EstadoHabitacion.MANTENIMIENTO;
                case 4:
                    return EstadoHabitacion.OTRO;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }

        return EstadoHabitacion.DISPONIBLE;  // Este caso nunca debería ocurrir debido al bucle
    }

    // Método estático para pedir datos de la reserva
    public static Reserva pedirDatosReserva (Hotel mihotel) {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String DniPasajero = null;
        Pasajero pasajero = null;
        LocalDate fechaInicio = null;
        LocalDate fechaFin = null;
        int numeroHabitacion = 0;

        boolean DNIpasajeroValido = false;
        while (!DNIpasajeroValido) {
            // Pedir ID del pasajero hasta que sea un entero válido
            System.out.println("Ingrese el DNI del pasajero:");
            DniPasajero = scanner.nextLine();
            pasajero = mihotel.buscarPasajeroPorDni(DniPasajero);
            if (pasajero != null)
            {
                DNIpasajeroValido = true;
            }
            else
            {
                System.out.println("DNI incorrecto. Ingreselo nuevamente");
            }
        }


    // Pedir fecha de inicio hasta que sea una fecha válida
    boolean fechaInicioValida = false;
        while(!fechaInicioValida)

    {
        System.out.println("Ingrese la fecha de inicio de la reserva (formato yyyy-MM-dd):");
        try {
            fechaInicio = LocalDate.parse(scanner.nextLine(), formatter);
            fechaInicioValida = true; // Si es válida, salimos del bucle
        } catch (DateTimeParseException e) {
            System.out.println("Formato de fecha no válido. Intente nuevamente.");
        }
    }

    // Pedir fecha de fin hasta que sea una fecha válida
    boolean fechaFinValida = false;
        while(!fechaFinValida)

    {
        System.out.println("Ingrese la fecha de fin de la reserva (formato yyyy-MM-dd):");
        try {
            fechaFin = LocalDate.parse(scanner.nextLine(), formatter);
            fechaFinValida = true; // Si es válida, salimos del bucle
        } catch (DateTimeParseException e) {
            System.out.println("Formato de fecha no válido. Intente nuevamente.");
        }
    }

    // Pedir número de habitación hasta que sea un entero válido
    boolean numeroHabitacionValido = false;
        while(!numeroHabitacionValido)

    {
        System.out.println("Ingrese el número de la habitación:");
        try {
            numeroHabitacion = scanner.nextInt();
            numeroHabitacionValido = true; // Si es válido, salimos del bucle
        } catch (InputMismatchException e) {
            System.out.println("Número de habitación no válido. Intente nuevamente.");
            scanner.nextLine(); // Limpiar el buffer si hubo error
        }
    }
        return new

    Reserva(pasajero.getId(), fechaInicio, fechaFin, numeroHabitacion,"habitacion reservada");
}

    // Método estático para pedir datos de la reserva del mantenimiento
    public static Reserva pedirDatosReservaMantenimiento (Hotel mihotel) {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate fechaInicio = null;
        LocalDate fechaFin = null;
        int numeroHabitacion = 0;
        String descripcion=null;


        // Pedir fecha de inicio hasta que sea una fecha válida
        boolean fechaInicioValida = false;
        while(!fechaInicioValida)

        {
            System.out.println("Ingrese la fecha de inicio de la reserva (formato yyyy-MM-dd):");
            try {
                fechaInicio = LocalDate.parse(scanner.nextLine(), formatter);
                fechaInicioValida = true; // Si es válida, salimos del bucle
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha no válido. Intente nuevamente.");
            }
        }

        // Pedir fecha de fin hasta que sea una fecha válida
        boolean fechaFinValida = false;
        while(!fechaFinValida)

        {
            System.out.println("Ingrese la fecha de fin de la reserva (formato yyyy-MM-dd):");
            try {
                fechaFin = LocalDate.parse(scanner.nextLine(), formatter);
                fechaFinValida = true; // Si es válida, salimos del bucle
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha no válido. Intente nuevamente.");
            }
        }

        // Pedir número de habitación hasta que sea un entero válido
        boolean numeroHabitacionValido = false;
        while(!numeroHabitacionValido)

        {
            System.out.println("Ingrese el número de la habitación:");
            try {
                numeroHabitacion = scanner.nextInt();
                numeroHabitacionValido = true; // Si es válido, salimos del bucle
            } catch (InputMismatchException e) {
                System.out.println("Número de habitación no válido. Intente nuevamente.");
                scanner.nextLine(); // Limpiar el buffer si hubo error
            }
        }

        System.out.println("Ingrese la descripcion del nmantenimiento:");
        descripcion = scanner.nextLine();

        return new

                Reserva(-3, fechaInicio, fechaFin, numeroHabitacion,descripcion);
    }

    // Método para generar una reserva
    public static String generarReservaMantenimiento(Hotel mihotel) throws HabitacionInexistenteException, PasajeroInexistenteException {
        Reserva aux = pedirDatosReserva(mihotel);
        System.out.println("Generando nueva reserva...");
        return mihotel.generarReserva(aux.getNumeroHabitacion(),-3, aux.getFechaInicio(), aux.getFechaFin(),aux.getDescripcion());
    }

    // Método para generar una reserva
    public static String generarReserva(Hotel mihotel) throws HabitacionInexistenteException, PasajeroInexistenteException {
        Reserva aux = pedirDatosReserva(mihotel);
        System.out.println("Generando nueva reserva...");
        return mihotel.generarReserva(aux.getNumeroHabitacion(), aux.getIdPasajero(), aux.getFechaInicio(), aux.getFechaFin(), aux.getDescripcion());
    }

    // Método para eliminar una reserva
    public static String eliminarReserva(Hotel mihotel) {
        Reserva aux = pedirDatosReserva(mihotel);
        System.out.println("Eliminado reserva...");
        return mihotel.eliminarReserva(aux.getNumeroHabitacion(), aux.getIdPasajero(), aux.getFechaInicio(), aux.getFechaFin());
    }


}
