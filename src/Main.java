import Clases.*;
import Enums.EstadoHabitacion;
import Exceptions.ListaVaciaException;

public class Main {
    //saber usar hash y set y mapas para el parcial
    //interfaces de ocupable
    public static void main(String[] args) {
        Hotel mihotel = new Hotel("Valles");
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
        mihotel.getUsuarios().agregar(admin.getId(), admin);
        // se ingresa una opcion no valida para comenzar
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
                                        System.out.println(mihotel.listarHabitaciones());
                                        break;
                                    case 3:
                                        System.out.println(mihotel.listarPasajeros());
                                        break;
                                    case 4:
                                        System.out.println(mihotel.listarConserjes());
                                        break;
                                    case 5:
                                        int opcionModificar = -1;
                                        while (opcionModificar != 0) {
                                            // Pedir el DNI del pasajero
                                            String dni = Menu.pedirDni();

                                            // Buscar el pasajero en el hotel
                                            Pasajero pasajero = mihotel.buscarPasajeroPorDni(dni);

                                            // Verificar si el pasajero existe
                                            if (pasajero == null) {
                                                // Si el pasajero no existe, mostrar un mensaje
                                                System.out.println("El pasajero con DNI " + dni + " no existe en el sistema.");
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
                                            String dni = Menu.pedirDni();

                                            // Buscar el conserje en el hotel
                                            Conserje conserje = (Conserje) mihotel.buscarConcerjePorDni(dni);

                                            // Verificar si el conserje existe
                                            if (conserje == null) {
                                                // Si el conserje no existe, mostrar un mensaje
                                                System.out.println("El conserje con DNI " + dni + " no existe en el sistema.");
                                            } else {
                                                // Si el conserje existe, mostrar el menú para modificar los datos
                                                opcionModificar = Menu.menuModificarConserje();

                                                // Procesar la opción seleccionada en el menú
                                                switch (opcionModificar) {
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
                    while (funcion != 0)
                    {
                        funcion = Menu.mostrarMenuConserje();
                        switch (funcion)
                        {
                            case 1:
                                System.out.println(mihotel.listarHabitacionesActualmenteOcupadas());
                                break;
                            case 2:
                                System.out.println(mihotel.listarHabitacionesDisponibles());
                                break;
                            case 3:
                                EstadoHabitacion estado = SolicitudDatos.obtenerEstadoHabitacion();
                                System.out.println(mihotel.listarHabitacionesOcupadasMotivo(estado));
                                break;
                            case 4:
                                // listar listado de reservas asosiadas a un pasajero
                                System.out.println("Ingrese tu nuevo dni:");
                                String nuevoDni = Menu.pedirDni();
                                System.out.println(mihotel.historialPasajero(nuevoDni));
                                break;
                            case 5:
                                // generar reserva
                                System.out.println(SolicitudDatos.generarReserva(mihotel));
                                break;
                            case 6:
                                // eliminar reserva
                                System.out.println(SolicitudDatos.eliminarReserva(mihotel));
                                break;
                            case 7:
                                // listar todas las habitaciones
                                System.out.println(mihotel.listarHabitaciones());
                                break;
                            case 8:
                                // listar todas las reservas
                                System.out.println(mihotel.listarReservas());
                                break;
                            case 9:
                                //Realizar check in
                                String dni = Menu.pedirDni();
                                System.out.println(mihotel.realizarCheckInCompleto(dni));
                                break;
                            case 10:
                                //Realizar check out
                                String dni2 = Menu.pedirDni();
                                System.out.println(mihotel.realizarCheckInCompleto(dni2));
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
    }
}


//CONSULTAS
//consultar listado de de habitaciones actualmente ocupadas
