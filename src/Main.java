import Clases.*;
import Enums.EstadoHabitacion;
import Enums.TipoHabitacion;
import Exceptions.HabitacionInexistenteException;
import Exceptions.ListaVaciaException;
import Exceptions.PasajeroInexistenteException;
import Exceptions.ReservaInexistenteException;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Main {
    //saber usar hash y set y mapas para el parcial
    //interfaces de ocupable
    public static void main(String[] args) {
        Hotel mihotel = new Hotel("NuestroHotel");
        Scanner scanner = new Scanner(System.in);
        /*
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
        */
        //SolicitudDatosCrearEntidades.crearPasajero(mihotel);
        //SolicitudDatosCrearEntidades.crearPasajero(mihotel);

        Administrador admin = new Administrador("46277918", "Juan Ignacio", "Juanjuli2");
        Habitacion nueva1 = new Habitacion(1, TipoHabitacion.SIMPLE, EstadoHabitacion.DISPONIBLE);
        Habitacion nueva2 = new Habitacion(2, TipoHabitacion.DOBLE, EstadoHabitacion.MANTENIMIENTO);
        Habitacion nueva3 = new Habitacion(3, TipoHabitacion.SUITE, EstadoHabitacion.OCUPADA);
        Habitacion nueva4 = new Habitacion(4, TipoHabitacion.SIMPLE, EstadoHabitacion.DISPONIBLE);
        Habitacion nueva5 = new Habitacion(5, TipoHabitacion.SIMPLE, EstadoHabitacion.OTRO);
        mihotel.getHabitaciones().agregar(1, nueva1);
        mihotel.getHabitaciones().agregar(2, nueva2);
        mihotel.getHabitaciones().agregar(3, nueva3);
        mihotel.getHabitaciones().agregar(4, nueva4);
        mihotel.getHabitaciones().agregar(5, nueva5);
        Pasajero nuevo1 = new Pasajero("Pedro", "Alfonso", "46277919", "Mar del Plata", "Salta 3949");
        Pasajero nuevo2 = new Pasajero("Manuel", "Segovia", "46277920", "Mar del Plata", "Ortiz de Zarate 7062");
        mihotel.getPasajeros().agregar(nuevo1.getId(), nuevo1);
        mihotel.getPasajeros().agregar(nuevo2.getId(), nuevo2);
        mihotel.crearConserjeNuevo("46277921", "Lucas");
        mihotel.getUsuarios().agregar(admin.getId(), admin);
        // se ingresa una opcion no valida para comenzar
        int arranque = -1;
        while (arranque != 0) {
            System.out.println("1. Iniciar sesión");
            System.out.println("0. Salir");
            System.out.print("Elija una opción: ");

            // Comprobamos si la entrada es válida (número entero)
            if (scanner.hasNextInt()) {
                arranque = scanner.nextInt();
                switch (arranque) {
                    case 1:
                        int opcionUsuario = -2;
                        while (opcionUsuario != 0) {
                            // se le solicita el dni
                            String dniIngresado = Menu.pedirDniInicio();
                            // se corrobora si el dni corresponde a un administrador o a un concerje
                            opcionUsuario = mihotel.buscarUsuarioPorDni(dniIngresado);
                            switch (opcionUsuario) {
                                case 1:
                                    // Si el usuario es administrador, llamamos al menú de administrador
                                    try {
                                        boolean rta = mihotel.verificarContraseñaAdministrador(dniIngresado, Menu.pedirContraseña());
                                        if (rta) {
                                            System.out.println("Accediendo al menú de Administrador...");
                                            int funcion = -1;
                                            System.out.println("Acceso concedido. Bienvenido al menú de Administrador.");
                                            while (funcion != 0) {
                                                funcion = Menu.mostrarMenuAdministrador();
                                                switch (funcion) {
                                                    case 1:
                                                        SolicitudDatos.crearHabitacion(mihotel);
                                                        break;
                                                    case 2:
                                                        try {
                                                            System.out.println(mihotel.listarHabitaciones());

                                                        } catch (ListaVaciaException e) {
                                                            System.out.println(e.getMessage());
                                                        }
                                                        break;
                                                    case 3:
                                                        try {
                                                            System.out.println(mihotel.listarPasajeros());
                                                        } catch (ListaVaciaException e) {
                                                            System.out.println(e.getMessage());
                                                        }
                                                        break;
                                                    case 4:
                                                        try {
                                                            System.out.println(mihotel.listarConserjes());
                                                        } catch (ListaVaciaException e) {
                                                            System.out.println(e.getMessage());
                                                        }
                                                        break;
                                                    case 5:
                                                        int opcionModificar = -1;
                                                        while (opcionModificar != 0) {
                                                            // Pedir el DNI del pasajero
                                                            System.out.println("Ingrese el dni del pasajero a modificar");
                                                            String dni = Menu.pedirDni();
                                                            // Buscar el pasajero en el hotel
                                                            Pasajero pasajero = mihotel.buscarPasajeroPorDni(dni);

                                                            // Verificar si el pasajero existe
                                                            if (pasajero == null) {
                                                                // Si el pasajero no existe, mostrar un mensaje
                                                                System.out.println("El pasajero con DNI " + dni + " no existe en el sistema.");
                                                                break;
                                                            } else {
                                                                // Si el pasajero existe, mostrar el menú para modificar los datos
                                                                opcionModificar = Menu.menuModificarPasajero();

                                                                // Procesar la opción seleccionada en el menú
                                                                switch (opcionModificar) {
                                                                    case 1:
                                                                        // Modificar el nombre del pasajero
                                                                        System.out.println("Ingrese el nuevo nombre del pasajero:");
                                                                        String nuevoNombre = Menu.pedirTexto();
                                                                        pasajero.setNombre(nuevoNombre);
                                                                        break;
                                                                    case 2:
                                                                        // Modificar el apellido del pasajero
                                                                        System.out.println("Ingrese el nuevo apellido del pasajero:");
                                                                        String nuevoApellido = Menu.pedirTexto();
                                                                        pasajero.setApellido(nuevoApellido);
                                                                        break;
                                                                    case 3:
                                                                        // Modificar el DNI del pasajero
                                                                        System.out.println("Ingrese el nuevo DNI del pasajero:");
                                                                        String nuevoDni = Menu.pedirDni();
                                                                        pasajero.setDni(nuevoDni);
                                                                        break;
                                                                    case 4:
                                                                        // Modificar el origen del pasajero
                                                                        System.out.println("Ingrese el nuevo origen del pasajero:");
                                                                        String nuevoOrigen = Menu.pedirTexto();
                                                                        pasajero.setOrigen(nuevoOrigen);
                                                                        break;
                                                                    case 5:
                                                                        // Modificar el domicilio de origen del pasajero
                                                                        System.out.println("Ingrese el nuevo domicilio de origen del pasajero:");
                                                                        String nuevoDomicilio = Menu.pedirTexto();
                                                                        pasajero.setDomicilioOrigen(nuevoDomicilio);
                                                                        break;
                                                                    case 0:
                                                                        // Salir de la opción de modificar
                                                                        break;
                                                                    default:
                                                                        System.out.println("Opción no válida, intente nuevamente.");
                                                                        break;
                                                                }
                                                                mihotel.getPasajeros().agregar(pasajero.getId(), pasajero);
                                                            }
                                                        }
                                                        break;
                                                    case 6:
                                                        int opcionModificar2 = -1;
                                                        while (opcionModificar2 != 0) {
                                                            // Pedir el DNI del conserje
                                                            System.out.println("Ingrese el dni del conserje a modificar");
                                                            String dni = Menu.pedirDni();

                                                            // Buscar el conserje en el hotel
                                                            Conserje conserje = (Conserje) mihotel.buscarConcerjePorDni(dni);

                                                            // Verificar si el conserje existe
                                                            if (conserje == null) {
                                                                // Si el conserje no existe, mostrar un mensaje
                                                                System.out.println("El conserje con DNI " + dni + " no existe en el sistema.");
                                                            } else {
                                                                // Si el conserje existe, mostrar el menú para modificar los datos
                                                                opcionModificar2 = Menu.menuModificarConserje();

                                                                // Procesar la opción seleccionada en el menú
                                                                switch (opcionModificar2) {
                                                                    case 1:
                                                                        // Modificar el nombre del conserje
                                                                        System.out.println("Ingrese el nuevo nombre del coserje:");
                                                                        String nuevoNombre = Menu.pedirTexto();
                                                                        conserje.setNombre(nuevoNombre);
                                                                        break;
                                                                    case 2:
                                                                        // Modificar el DNI del conserje
                                                                        System.out.println("Ingrese el nuevo DNI del conserje:");
                                                                        String nuevoDni = Menu.pedirDni();
                                                                        conserje.setDni(nuevoDni);
                                                                        break;
                                                                    case 0:
                                                                        // Salir de la opción de modificar
                                                                        break;
                                                                    default:
                                                                        System.out.println("Opción no válida, intente nuevamente.");
                                                                        break;
                                                                }
                                                                mihotel.getUsuarios().agregar(conserje.getId(), conserje);
                                                            }
                                                        }
                                                        break;
                                                    case 7:
                                                        int opcionModificar3 = -1;
                                                        while (opcionModificar3 != 0) {

                                                            // Buscar el conserje en el hotel
                                                            Administrador administrador = (Administrador) mihotel.buscarAdministradorPorDni(dniIngresado);

                                                            //mostrar el menú para modificar los datos
                                                            opcionModificar = Menu.menuModificarAdministrador();

                                                            // Procesar la opción seleccionada en el menú
                                                            switch (opcionModificar) {
                                                                case 1:
                                                                    // Modificar el nombre del admin
                                                                    System.out.println("Ingrese tu nuevo nombre:");
                                                                    String nuevoNombre = Menu.pedirTexto();
                                                                    administrador.setNombre(nuevoNombre);
                                                                    break;
                                                                case 2:
                                                                    // Modificar el DNI del admin
                                                                    System.out.println("Ingrese tu nuevo dni:");
                                                                    String nuevoDni = Menu.pedirDni();
                                                                    administrador.setDni(nuevoDni);
                                                                    break;
                                                                case 3:
                                                                    // Modificar la CONTRASEÑA del admin
                                                                    System.out.println("Ingrese tu nueva contraseña:");
                                                                    String contra = Menu.pedirTexto();
                                                                    administrador.setContraseña(contra);
                                                                    break;
                                                                case 0:
                                                                    // Salir de la opción de modificar
                                                                    break;
                                                                default:
                                                                    System.out.println("Opción no válida, intente nuevamente.");
                                                                    break;
                                                            }
                                                            mihotel.getUsuarios().agregar(administrador.getId(), administrador);
                                                        }
                                                        break;
                                                    case 8:
                                                        SolicitudDatos.crearConserje(mihotel);
                                                        break;
                                                    case 0:
                                                        System.out.println("Cerrando seccion...");
                                                        break;
                                                }
                                            }

                                        } else {
                                            System.out.println("contraseña incorrecta");
                                        }

                                    } catch (ListaVaciaException e) {
                                        System.out.println(e.getMessage());
                                    }
                                    break;
                                case 2:
                                    int funcion = -1;
                                    // Si el usuario es conserje, llamamos al menú de conserje
                                    System.out.println("Accediendo al menú de Conserje...");
                                    while (funcion != 0) {
                                        funcion = Menu.mostrarMenuConserje();
                                        switch (funcion) {
                                            case 1:
                                                try {
                                                    System.out.println(mihotel.listarHabitacionesOcupadas());

                                                } catch (ListaVaciaException e) {
                                                    System.out.println(e.getMessage());
                                                }
                                                break;
                                            case 2:
                                                try {
                                                    System.out.println(mihotel.listarHabitacionesDisponibles());
                                                } catch (ListaVaciaException e) {
                                                    System.out.println(e.getMessage());
                                                }
                                                break;
                                            case 3:
                                                try {
                                                    EstadoHabitacion estado = SolicitudDatos.obtenerEstadoHabitacion();
                                                    System.out.println(mihotel.listarHabitacionesOcupadasMotivo(estado));
                                                } catch (ListaVaciaException e) {
                                                    System.out.println(e.getMessage());
                                                }

                                                break;
                                            case 4:
                                                // listar listado de reservas asosiadas a un pasajero
                                                System.out.println("Ingrese tu nuevo dni:");
                                                String nuevoDni = Menu.pedirDni();
                                                System.out.println(mihotel.historialPasajero(nuevoDni));
                                                break;
                                            case 5:
                                                try {
                                                    // generar reserva
                                                    System.out.println(SolicitudDatos.generarReserva(mihotel));
                                                } catch (HabitacionInexistenteException |
                                                         PasajeroInexistenteException e) {
                                                    System.out.println(e.getMessage());
                                                }

                                                break;
                                            case 6:
                                                try {
                                                    // eliminar reserva
                                                    System.out.println(SolicitudDatos.eliminarReserva(mihotel));
                                                } catch (HabitacionInexistenteException |
                                                         PasajeroInexistenteException e) {
                                                    System.out.println(e.getMessage());
                                                }

                                                break;
                                            case 7:
                                                // listar todas las habitaciones
                                                try {
                                                    System.out.println(mihotel.listarHabitaciones());
                                                } catch (ListaVaciaException e) {
                                                    System.out.println(e.getMessage());
                                                }
                                                break;
                                            case 8:
                                                // listar todas las reservas
                                                try {
                                                    System.out.println(mihotel.listarReservas());
                                                } catch (ListaVaciaException e) {
                                                    System.out.println(e.getMessage());
                                                }

                                                break;
                                            case 9:
                                                //Realizar check in
                                                try {
                                                    System.out.println("Ingrese el dni del pasajero");
                                                    String dni = Menu.pedirDni();
                                                    System.out.println("Ingrese el nro de habitacion");
                                                    int nro_habitacion = Menu.pedirEntero();
                                                    System.out.println(mihotel.realizarCheckInCompleto(dni, nro_habitacion));
                                                } catch (HabitacionInexistenteException | PasajeroInexistenteException |
                                                         InputMismatchException | ReservaInexistenteException e) {
                                                    System.out.println(e.getMessage());
                                                }
                                                break;
                                            case 10:
                                                try {
                                                    //Realizar check out
                                                    System.out.println("Ingrese el dni del pasajero");
                                                    String dni2 = Menu.pedirDni();
                                                    System.out.println("Ingrese el nro de habitacion");
                                                    int nro_habitacion2 = Menu.pedirEntero();
                                                    System.out.println(mihotel.realizarCheckOutCompleto(dni2, nro_habitacion2));

                                                } catch (HabitacionInexistenteException | PasajeroInexistenteException |
                                                         InputMismatchException | ReservaInexistenteException e) {
                                                    System.out.println(e.getMessage());
                                                }

                                                break;
                                            case 11:
                                                // crear pasajero
                                                SolicitudDatos.crearPasajero(mihotel);
                                                break;
                                            case 12:
                                                // crear pasajero y hacer reserva
                                                SolicitudDatos.crearPasajero(mihotel);
                                                System.out.println(SolicitudDatos.generarReserva(mihotel));
                                                break;
                                            case 0:
                                                System.out.println("Cerrando seccion...");
                                                break;
                                        }
                                    }

                                    break;
                                case -1:
                                    System.out.println("El dni no corresponde con ningun usuario");
                                    break;
                                case 3:
                                    // Si el usuario elige salir, el programa termina
                                    System.out.println("Saliendo del programa...");
                                    break;
                            }

                        }
                        break;
                    case 0:

                        break;
                }
            }
            mihotel.cargarArch();
        }
    }


    public static Hotel fromJson(String filePath) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(filePath)) {
            // Leer el JSON y convertirlo en un JsonElement
            JsonElement jsonElement = gson.fromJson(reader, JsonElement.class);

            // Asegurarse de que el JSON tiene la estructura correcta
            JsonObject jsonObject = jsonElement.getAsJsonObject();

            // Deserializar el JSON en un objeto Hotel
            return gson.fromJson(jsonObject, Hotel.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
//CONSULTAS
//consultar listado de de habitaciones actualmente ocupadas
