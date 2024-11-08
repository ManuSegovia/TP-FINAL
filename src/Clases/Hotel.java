package Clases;

import Enums.EstadoHabitacion;
import Enums.TipoHabitacion;
import Exceptions.HabitacionInexistenteException;
import Exceptions.PasajeroInexistenteException;

import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Hotel {
    //nuevo
    private String nombre;
    private GestorDeDatos<Habitacion> habitaciones;
    private GestorDeDatos<Pasajero> pasajeros;
    private GestorDeReservas<Reserva> reservas;

    public Hotel(String nombre) {
        this.nombre = nombre;
        this.habitaciones = new GestorDeDatos<Habitacion>();
        this.pasajeros = new GestorDeDatos<Pasajero>();
        this.reservas = new GestorDeReservas<Reserva>();
    }

// Cuestiones relacionadas a las reservas  -------------------------------------------------------------------------------------------

    public String listarHabitacionesActualmenteOcupadas() {
        return reservas.listarHabitacionesOcupadas();
    }

    public String listarHabitacionesDisponibles() {
        return reservas.listarHabitacionesDisponibles();
    }

    public String listarHabitacionesOcupadasMotivo(EstadoHabitacion motivo) {
        return reservas.listarHabitacionesOcupadasMotivo(motivo);
    }
    // funcion para crear un pasajero y comprobar
    public Pasajero crearPasajero() {
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
            boolean pasajeroExistente = false;
            for (Pasajero pasajero : pasajeros.getElementos().values()) {
                if (pasajero.getDni().equals(dni)) {
                    // Si ya existe un pasajero con el mismo DNI, notificar al conserje
                    System.out.println("El pasajero con DNI " + dni + " ya existe en el sistema. Por favor, ingrese un DNI diferente.");
                    pasajeroExistente = true;
                    break; // Salir del ciclo y pedir los datos nuevamente
                }
            }

            if (!pasajeroExistente) {
                // Si el DNI es único, pedir más datos
                System.out.println("Ingrese el origen del pasajero:");
                String origen = scanner.nextLine();

                System.out.println("Ingrese el domicilio de origen del pasajero:");
                String domicilioOrigen = scanner.nextLine();

                // Crear un nuevo objeto Pasajero con los datos proporcionados
                nuevoPasajero = new Pasajero(nombre, apellido, dni, origen, domicilioOrigen);

                // Almacenar al pasajero en el mapa
                pasajeros.agregar(nuevoPasajero.getId(), nuevoPasajero);

                // Retornar el objeto Pasajero creado
                System.out.println("Pasajero creado exitosamente con el ID: " + nuevoPasajero.getId());
            }
        }
        return nuevoPasajero;
    }


    //ocupar una habitación en un período determinado (consultando la ocupación y las reservas).

    public String generarReserva(int numeroHabtacion, int idPasajero, LocalDate fechaReserva, LocalDate fechaFinReserva) throws HabitacionInexistenteException, PasajeroInexistenteException {
        if (habitaciones.buscar(numeroHabtacion) == null) {
            throw new HabitacionInexistenteException();
        }
        if (pasajeros.buscar(idPasajero) == null) {
            throw new PasajeroInexistenteException();
        }
        Reserva nueva = new Reserva(idPasajero, fechaReserva, fechaFinReserva, numeroHabtacion);
        String rtdo = reservas.agregar(numeroHabtacion, nueva);
        return rtdo;
    }

    public String generarReservaConCreacionDePasajero(int numeroHabitacion, LocalDate fechaReserva, LocalDate fechaFinReserva) throws HabitacionInexistenteException, PasajeroInexistenteException {
        // Verificar si la habitación existe
        int idPasajero = 0;
        if (habitaciones.buscar(numeroHabitacion) == null) {
            throw new HabitacionInexistenteException();
        }

        // Verificar si el pasajero existe
        Pasajero pasajero = crearPasajero();
        // Crear la reserva
        Reserva nuevaReserva = new Reserva(pasajero.getId(), fechaReserva, fechaFinReserva, numeroHabitacion);

        // Guardar la nueva reserva
        String resultado = reservas.agregar(numeroHabitacion, nuevaReserva);

        return resultado;
    }

    public String eliminarReserva(int numeroHabtacion, int idPasajero, LocalDate fechaReserva, LocalDate fechaFinReserva) throws HabitacionInexistenteException, PasajeroInexistenteException {
        if (habitaciones.buscar(numeroHabtacion) == null) {
            throw new HabitacionInexistenteException();
        }
        if (pasajeros.buscar(idPasajero) == null) {
            throw new PasajeroInexistenteException();
        }
        String rtdo = reservas.eliminar(numeroHabtacion, idPasajero, fechaReserva, fechaFinReserva);
        return rtdo;
    }

    //---------------------------------------------------------------------------------------------------------------------------------
    // Cuestiones relacionandas con las habitaciones (Parte concerje) -----------------------------------------------------------------

    public String listarHabitaciones() {
        return habitaciones.listar();
    }

    public String listarPasajeros() {
        return pasajeros.listar();
    }

    public String listarReservas() {
        return reservas.listar();
    }

    // Método para realizar el check-in completo
    public String realizarCheckInCompleto(String dniPasajero) {

        for (Map.Entry<Integer, Pasajero> entry : pasajeros.getElementos().entrySet()) {
            // Verificamos si el pasajero tiene el DNI que estamos buscando
            Pasajero pasajero = (Pasajero) entry.getValue();  // Suponiendo que T es de tipo Pasajero

            if (pasajero.getDni().equals(dniPasajero)) {
                // Encontramos el pasajero, ahora podemos realizar la reserva
                int id_Pasajero = pasajero.getId();

                // Buscar la reserva del pasajero por DNI
                Reserva reserva = reservas.buscarReservaPorPasajero(id_Pasajero);

                if (reserva == null) {
                    return "No se encuentra ninguna reserva para el DNI proporcionado.";
                }

                // Verificar si la fecha de inicio es válida para el check-in
                String mensajeCheckIn = reserva.realizar_CHECKIN();
                if (mensajeCheckIn.contains("correctamente")) {
                    // Si el check-in se realizó correctamente, cambiamos el estado de la habitación
                    Habitacion habitacion = habitaciones.buscar(reserva.getNumeroHabitacion());
                    if (habitacion != null) {
                        String mensaje = habitacion.cambiarEstadoHabitacion();
                        habitaciones.agregar(reserva.getNumeroHabitacion(), habitacion);
                        reservas.agregar(reserva.getNumeroHabitacion(), reserva);
                        return "Check-in realizado y se cambio la habitacion se encuentra: " + mensaje;
                    } else {
                        return "La habitación no existe.";
                    }
                } else {
                    // Si no se pudo realizar el check-in
                    return mensajeCheckIn;
                }
            }
        }
        // Si no encontramos el pasajero
        return "Pasajero no encontrado.";
    }

    public String realizarCheckOutCompleto(String dniPasajero) {

        // Recorremos los pasajeros para encontrar al que tiene el DNI proporcionado
        for (Map.Entry<Integer, Pasajero> entry : pasajeros.getElementos().entrySet()) {
            Pasajero pasajero = (Pasajero) entry.getValue();  // Suponiendo que T es de tipo Pasajero

            // Verificamos si el DNI del pasajero coincide con el que nos dieron
            if (pasajero.getDni().equals(dniPasajero)) {
                // Encontramos el pasajero, ahora podemos proceder con la reserva
                int id_Pasajero = pasajero.getId();

                // Buscar la reserva del pasajero
                Reserva reserva = reservas.buscarReservaPorPasajero(id_Pasajero);

                if (reserva == null) {
                    return "No se encuentra ninguna reserva para el DNI proporcionado.";
                }

                // Verificar si la fecha de finalización es válida para el check-out
                String mensajeCheckOut = reserva.realizar_CHECKOUT();
                if (mensajeCheckOut.contains("correctamente")) {
                    // Si el check-out se realizó correctamente, cambiamos el estado de la habitación
                    Habitacion habitacion = habitaciones.buscar(reserva.getNumeroHabitacion());
                    if (habitacion != null) {
                        // Cambiamos el estado de la habitación a DISPONIBLE
                        String mensajeCambioEstado = habitacion.cambiarEstadoHabitacion();
                        // Actualizamos el estado de la habitación en el mapa
                        habitaciones.agregar(reserva.getNumeroHabitacion(), habitacion);  // Aseguramos que se actualice en el mapa

                        // También aseguramos que la reserva esté correctamente almacenada (aunque podría no ser necesario si ya está almacenada)
                        reservas.agregar(reserva.getNumeroHabitacion(), reserva);

                        return "Check-out realizado correctamente. La habitación ahora está disponible. " + mensajeCambioEstado;
                    } else {
                        return "La habitación no existe.";
                    }
                } else {
                    // Si no se pudo realizar el check-out
                    return mensajeCheckOut;
                }
            }
        }
        // Si no encontramos el pasajero con ese DNI
        return "Pasajero no encontrado.";
    }


    // Cuestiones relacionandas con las habitaciones (Parte admin) -----------------------------------------------------------------

    public String crearHabitacionNueva(Administrador admin, int numero, TipoHabitacion tipoHabitacion, EstadoHabitacion estadoHabitacion) {
        if (habitaciones.buscarBoolean(numero)) {
            return "La habitacion ya existe";
        } else {
            Habitacion HabitacionNueva = admin.crearHabitacion(numero, tipoHabitacion, estadoHabitacion);
            habitaciones.agregar(numero, HabitacionNueva);
            return "Se creo correctamente la habitacion " + numero;
        }
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public GestorDeDatos<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(GestorDeDatos<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }

    public GestorDeDatos<Pasajero> getPasajeros() {
        return pasajeros;
    }

    public void setPasajeros(GestorDeDatos<Pasajero> pasajeros) {
        this.pasajeros = pasajeros;
    }

    public GestorDeReservas<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(GestorDeReservas<Reserva> reservas) {
        this.reservas = reservas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotel hotel = (Hotel) o;
        return Objects.equals(nombre, hotel.nombre) && Objects.equals(habitaciones, hotel.habitaciones) && Objects.equals(pasajeros, hotel.pasajeros) && Objects.equals(reservas, hotel.reservas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, habitaciones, pasajeros, reservas);
    }

    @Override
    public String toString() {
        return "Clases.Hotel{" +
                "nombre='" + nombre + '\'' +
                ", habitaciones=" + habitaciones +
                ", pasajeros=" + pasajeros +
                ", reservas=" + reservas +
                '}';
    }
}
