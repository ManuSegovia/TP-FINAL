package Clases;

import Enums.EstadoHabitacion;
import Enums.TipoHabitacion;
import Enums.TipoUsuario;
import Exceptions.HabitacionInexistenteException;
import Exceptions.PasajeroInexistenteException;
import Exceptions.ListaVaciaException;

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
    private GestorDeDatos<Usuario> usuarios;

    public Hotel(String nombre) {
        this.nombre = nombre;
        this.habitaciones = new GestorDeDatos<Habitacion>();
        this.pasajeros = new GestorDeDatos<Pasajero>();
        this.reservas = new GestorDeReservas<Reserva>();
        this.usuarios = new GestorDeDatos<Usuario>();
    }

    // funciones para buscar usuario segun dni y para retornarlo -------------------------------------------------------------------

    public int buscarUsuarioPorDni(String dni) {
        for (Map.Entry<Integer, Usuario> entry : usuarios.getElementos().entrySet()) {
            // Comprobamos si el DNI coincide
            if (entry.getValue().getDni().equals(dni)) {
                // Si coincide, retornamos el tipo de usuario
                Usuario usuario = entry.getValue();
                if (usuario.getTipoUsuario() == TipoUsuario.ADMINISTRADOR) {
                    return 1;  // Es administrador
                } else if (usuario.getTipoUsuario() == TipoUsuario.CONSERJE) {
                    return 0;  // Es conserje
                }
            }
        }
        return -1;  // Si no encuentra el usuario, devuelve -1
    }

    public Usuario buscarUsuarioRetornarlo(String dni) {
        for (Map.Entry<Integer, Usuario> entry : usuarios.getElementos().entrySet()) {
            // Comprobamos si el DNI coincide
            if (entry.getValue().getDni().equals(dni)) {
                // Si coincide, retornamos el usuario
                return entry.getValue();
            }
        }
        return null;  // Si no encuentra el usuario, devuelve null
    }

    public Pasajero buscarPasajeroPorDni(String dni) {
        for (Map.Entry<Integer, Pasajero> entry : pasajeros.getElementos().entrySet()) {
            // Comprobamos si el DNI coincide
            if (entry.getValue().getDni().equals(dni)) {
                // Si coincide, retornamos el usuario
                return entry.getValue();
            }
        }
        return null;  // Si no encuentra el usuario, devuelve null
    }

    public Usuario buscarConcerjePorDni(String dni) {
        for (Map.Entry<Integer, Usuario> entry : usuarios.getElementos().entrySet()) {
            // Comprobamos si el DNI coincide
            if (entry.getValue().getDni().equals(dni) && entry.getValue().getTipoUsuario() == TipoUsuario.CONSERJE) {
                // Si coincide, retornamos el usuario
                return entry.getValue();
            }
        }
        return null;  // Si no encuentra el usuario, devuelve null
    }

    public Usuario buscarAdministradorPorDni(String dni) {
        for (Map.Entry<Integer, Usuario> entry : usuarios.getElementos().entrySet()) {
            // Comprobamos si el DNI coincide
            if (entry.getValue().getDni().equals(dni) && entry.getValue().getTipoUsuario() == TipoUsuario.ADMINISTRADOR) {
                // Si coincide, retornamos el usuario
                return entry.getValue();
            }
        }
        return null;  // Si no encuentra el usuario, devuelve null
    }

    // funcion para buscar usuarios en el inicio de seccion-------------------------------------------------------------------------------

    public boolean verificarContraseñaAdministrador(String dni, String contra) throws ListaVaciaException {
        Administrador usuarioAux = (Administrador) buscarUsuarioRetornarlo(dni);
        if (usuarioAux == null) {
            throw new ListaVaciaException("Actualmente no hay administradores registrados en el sistema");
        }
        return Login.comprobarContraseñas(contra, usuarioAux.getContraseña());
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
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("HABITACIONES").append("\n");
        mensaje.append(habitaciones.listar());
        return mensaje.toString();
    }

    public String listarPasajeros() {
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("PASAJEROS").append("\n");
        mensaje.append(pasajeros.listar());
        return mensaje.toString();
    }

    public String listarReservas() {
        return reservas.listar();
    }

    public String listarConserjes() {
        StringBuilder sb = new StringBuilder();
        boolean hayConserjes = false; // Variable para verificar si hay conserjes

        for (Usuario usuario : usuarios.getElementos().values()) {
            if (usuario.getTipoUsuario() == TipoUsuario.CONSERJE) {
                sb.append(usuario.getNombre()).append("\n");
                hayConserjes = true; // Si encontramos un conserje, cambiamos el estado
            }
        }

        // Si no se han agregado conserjes, agregamos el mensaje
        if (!hayConserjes) {
            sb.append("No hay conserjes cargados en el sistema");
        }

        return sb.toString();
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

    public String crearHabitacionNueva(int numero, TipoHabitacion tipoHabitacion, EstadoHabitacion estadoHabitacion) {
        if (habitaciones.buscarBoolean(numero)) {
            return "La habitacion ya existe";
        } else {
            Habitacion HabitacionNueva = Administrador.crearHabitacion(numero, tipoHabitacion, estadoHabitacion);
            habitaciones.agregar(numero, HabitacionNueva);
            return "Se creo correctamente la habitacion " + numero;
        }
    }

    // Cambiar el estado de una habitación
    public String cambiarEstadoHabitacion(int numeroHabitacion, EstadoHabitacion nuevoEstado) {
        Habitacion habitacion = habitaciones.getElementos().get(numeroHabitacion);
        if (habitacion != null) {
            habitacion.setEstadoHabitacion(nuevoEstado);
            return "El estado de la habitación " + numeroHabitacion + " ha sido actualizado a " + nuevoEstado + ".";
        } else {
            return "La habitación " + numeroHabitacion + " no existe.";
        }
    }

    // Cambiar el tipo de una habitación
    public String cambiarTipoHabitacion(int numeroHabitacion, TipoHabitacion nuevoTipo) {
        Habitacion habitacion = habitaciones.getElementos().get(numeroHabitacion);
        if (habitacion != null) {
            habitacion.setTipoHabitacion(nuevoTipo);
            return "El tipo de la habitación " + numeroHabitacion + " ha sido actualizado a " + nuevoTipo + ".";
        } else {
            return "La habitación " + numeroHabitacion + " no existe.";
        }
    }

    // Cambiar el número de una habitación
    public String cambiarNumeroHabitacion(int numeroActual, int nuevoNumero) {
        if (!habitaciones.getElementos().containsKey(numeroActual)) {
            return "La habitación " + numeroActual + " no existe.";
        }
        if (habitaciones.getElementos().containsKey(nuevoNumero)) {
            return "Ya existe una habitación con el número " + nuevoNumero + ".";
        }

        // Cambiar el número de la habitación en el mapa
        Habitacion habitacion = habitaciones.getElementos().remove(numeroActual);
        habitacion.setNumero(nuevoNumero); // Aquí necesitas un setNumero en la clase Habitacion
        habitaciones.getElementos().put(nuevoNumero, habitacion);

        return "El número de la habitación ha sido cambiado de " + numeroActual + " a " + nuevoNumero + ".";
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

    public GestorDeDatos<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(GestorDeDatos<Usuario> usuarios) {
        this.usuarios = usuarios;
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
