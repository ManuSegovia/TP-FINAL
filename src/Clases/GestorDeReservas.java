package Clases;

import Exceptions.ListaVaciaException;
import org.json.JSONArray;
import org.json.JSONObject;
import Enums.EstadoHabitacion;

import java.time.LocalDate;
import java.util.*;

public class GestorDeReservas<T> {
    private Map<Integer, ArrayList<T>> listadoHabitaciones;

    public GestorDeReservas() {
        this.listadoHabitaciones = new HashMap<>();
    }

    public String agregar(int numeroHabitacion, T elemento) {
        Reserva cargar = (Reserva) elemento;

        // Validar que la fecha de inicio no sea anterior a la fecha actual
        if (cargar.getFechaInicio().isBefore(LocalDate.now())) {
            return "La fecha de inicio no puede ser anterior a la fecha de hoy";
        }

        // Validar que la fecha de fin no sea antes de la fecha de inicio
        if (cargar.getFechaFin().isBefore(cargar.getFechaInicio())) {
            return "La fecha de fin no puede ser anterior a la fecha de inicio";
        }

        // Verificar si la habitación existe en el listado
        if (!listadoHabitaciones.containsKey(numeroHabitacion)) {
            listadoHabitaciones.put(numeroHabitacion, new ArrayList<>());
        } else {
            ArrayList<Reserva> reservasAux = (ArrayList<Reserva>) listadoHabitaciones.get(numeroHabitacion);

            // Recorremos las reservas actuales para verificar si hay solapamientos
            Iterator<Reserva> iterator = reservasAux.iterator();
            while (iterator.hasNext()) {
                Reserva reserva = iterator.next();
                if ((cargar.getFechaInicio().isBefore(reserva.getFechaFin()) && cargar.getFechaFin().isAfter(reserva.getFechaInicio())) ||
                        (cargar.getFechaInicio().isEqual(reserva.getFechaInicio()) || cargar.getFechaFin().isEqual(reserva.getFechaFin()))) {
                    return "La habitación no se puede reservar en esa fecha"; // Si se solapan, no agregamos la reserva
                }
            }
        }

        // Si pasó todas las validaciones, agregamos la reserva
        listadoHabitaciones.get(numeroHabitacion).add((T) cargar);
        return "Se reservó correctamente la habitación";
    }


    public String eliminar(int key, int id_Pasajero, LocalDate fechaInicio, LocalDate fecha_Fin) {
        // Verificamos si la clave existe en el mapa
        if (listadoHabitaciones.containsKey(key)) {
            // Recuperamos la lista de reservas asociada a esa clave, haciendo un casting seguro
            ArrayList<Reserva> reservasAux = (ArrayList<Reserva>) listadoHabitaciones.get(key);

            // Utilizamos un iterador para eliminar elementos de la lista de forma segura
            Iterator<Reserva> iterator = reservasAux.iterator();
            while (iterator.hasNext()) {
                Reserva reserva = iterator.next();
                if (reserva.getIdPasajero() == id_Pasajero && reserva.getFechaInicio().isEqual(fechaInicio) && reserva.getFechaFin().isEqual(fecha_Fin)) {
                    iterator.remove(); // Eliminamos la reserva encontrada
                    return "Se elimino correctamente la reserva a la habitacion " + reserva.getNumeroHabitacion() + " a nombre del pasajero " + id_Pasajero;
                }
            }
        }
        return " No se encontro una habitacion asociada a estos datos";
    }


    public String listar() throws ListaVaciaException{
        StringBuilder mensaje = new StringBuilder();
        if (listadoHabitaciones.isEmpty()) {
            throw new ListaVaciaException("Actualemente no hay reservar registradas");
        } else {
            Iterator<Map.Entry<Integer, ArrayList<T>>> iterador = listadoHabitaciones.entrySet().iterator();
            while (iterador.hasNext()) {
                Map.Entry<Integer, ArrayList<T>> entrada = iterador.next();
                if (entrada.getValue().isEmpty()) {

                } else {
                    mensaje = mensaje.append("\n" + "Habitacion: " + entrada.getKey() + "\n");
                    for (T elemento : entrada.getValue()) {
                        mensaje = mensaje.append(elemento.toString()).append("\n");
                    }
                }
                if (mensaje.isEmpty())
                {

                    throw new ListaVaciaException("Actualemente no hay reservar registradas");
                }
            }
        }
        return mensaje.toString();
    }


    public boolean habitacionDisponibleFechas(int key, LocalDate inicioReserva, LocalDate finReserva) {
        // Verifica si la clave de la habitación existe en el listado
        if (listadoHabitaciones.containsKey(key)) {
            // Obtiene las reservas de esa habitación
            ArrayList<Reserva> reservas = (ArrayList<Reserva>) listadoHabitaciones.get(key);

            // Recorre todas las reservas de la habitación
            for (Reserva reserva : reservas) {
                // Compara si las fechas de la nueva reserva se solapan con alguna reserva existente
                // La reserva nueva no debe solaparse con ninguna reserva existente
                if ((inicioReserva.isBefore(reserva.getFechaFin()) && finReserva.isAfter(reserva.getFechaInicio())) ||
                        (inicioReserva.isEqual(reserva.getFechaInicio()) || finReserva.isEqual(reserva.getFechaFin()))) {
                    // Si las fechas se solapan, la habitación no está disponible
                    return false;
                }
            }
            // Si no hay solapamiento, la habitación está disponible
            return true;
        } else {
            // Si no existe la habitación en el listado, la habitación no está disponible
            return false;
        }
    }

    public int comparar(int key, T elemento) {
        Reserva buscar = (Reserva) elemento;
        ArrayList<Reserva> reservas = (ArrayList<Reserva>) listadoHabitaciones.get(key);
        for (int i = 0; i < reservas.size(); i++) {
            if (reservas.get(i).equals(buscar)) {
                return i;
            }
        }
        return -1;
    }

    public Reserva buscarReservaPorPasajero(int idPasajero, int habitacion) {
        // Iterar sobre el mapa de habitaciones
        for (Map.Entry<Integer, ArrayList<T>> entry : listadoHabitaciones.entrySet()) {
            // Obtener la lista de reservas para esta habitación
            ArrayList<T> reservas = entry.getValue();

            // Buscar en la lista de reservas si alguna tiene el idPasajero igual al proporcionado
            for (T reserva : reservas) {
                if (reserva instanceof Reserva) {
                    Reserva reservaActual = (Reserva) reserva;
                    if (reservaActual.getIdPasajero() == idPasajero && reservaActual.getNumeroHabitacion() == habitacion) {
                        // Si encontramos la reserva, la retornamos
                        return reservaActual;
                    }
                }
            }
        }
        // Si no se encuentra ninguna reserva para el ID de pasajero, retornamos null
        return null;
    }

    public String listarHistorialPasajero(int idPasajero) {
        StringBuilder mensaje = new StringBuilder();
        if (listadoHabitaciones.isEmpty()) {
            return "No hay reservas registradas a este pasajero";
        } else {
            Iterator<Map.Entry<Integer, ArrayList<T>>> iterador = listadoHabitaciones.entrySet().iterator();
            while (iterador.hasNext()) {
                Map.Entry<Integer, ArrayList<T>> entrada = iterador.next();

                for (T elemento : entrada.getValue()) {
                    if (elemento instanceof Reserva) {
                        Reserva reserva = (Reserva) elemento;
                        if (reserva.getIdPasajero() == idPasajero) {
                            mensaje = mensaje.append("").append(elemento.toString()).append("\n");
                        }
                    }

                }
            }
            if (mensaje.isEmpty()) {
                return "El pasajero no tiene ninguna reserva asosiada";
            }
        }
        return mensaje.toString();
    }



    public JSONArray toJSON() {
        // Convertir el Map a un JSONArray
        JSONArray reservasJSON = new JSONArray();
        for (Map.Entry<Integer, ArrayList<T>> entry : listadoHabitaciones.entrySet()) {
            JSONObject reservaListJSON = new JSONObject();
            reservaListJSON.put("id", entry.getKey()); // La clave del Map

            // Convertir la lista de reservas a un JSONArray
            JSONArray reservasArrayJSON = new JSONArray();
            for (T reserva : entry.getValue()) {
                if (reserva instanceof Reserva) {
                    reservasArrayJSON.put(((Reserva) reserva).toJSON()); // Asumiendo que la clase Reserva tiene un método toJSON()
                }
                // Si T es otro tipo de objeto, deberás agregar su respectiva serialización.
            }
            reservaListJSON.put("reservasAsignadas", reservasArrayJSON);
            reservasJSON.put(reservaListJSON);
        }

        return reservasJSON;
    }

    public Map<Integer, ArrayList<T>> getElementos() {
        return listadoHabitaciones;
    }

    public Map<Integer, ArrayList<T>> getListadoHabitaciones() {
        return listadoHabitaciones;
    }

    public void setListadoHabitaciones(Map<Integer, ArrayList<T>> listadoHabitaciones) {
        this.listadoHabitaciones = listadoHabitaciones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GestorDeReservas<?> that = (GestorDeReservas<?>) o;
        return Objects.equals(listadoHabitaciones, that.listadoHabitaciones);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(listadoHabitaciones);
    }

    @Override
    public String toString() {
        return "GestorDeReservas:\n" +
                "ListadoHabitaciones:" + listadoHabitaciones;
    }

    //consultar listado de de habitaciones actualmente ocupadas
}
