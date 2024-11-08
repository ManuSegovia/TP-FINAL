package Clases;

import Enums.EstadoHabitacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GestorDeReservas <T>
{
    private Map<Integer, ArrayList<T>> listadoHabitaciones;

    public GestorDeReservas()
    {
        this.listadoHabitaciones =new HashMap<>();
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


    public String listar()
    {
        StringBuilder mensaje=new StringBuilder();
        if(listadoHabitaciones.isEmpty())
        {
            return "No hay nada que mostrar";
        }
        else
        {
            Iterator<Map.Entry<Integer,ArrayList<T>>>iterador= listadoHabitaciones.entrySet().iterator();
            while (iterador.hasNext())
            {
                Map.Entry<Integer,ArrayList<T>> entrada = iterador.next();
                mensaje= mensaje.append("\n" + "Habitacion: " + entrada.getKey() + "\n");
                for (T elemento: entrada.getValue())
                {
                    mensaje=mensaje.append(elemento.toString()).append("\n");
                }
            }
        }
        return mensaje.toString();
    }

    public String listarHabitacionesOcupadas()
    {
        StringBuilder mensaje=new StringBuilder();
        if(listadoHabitaciones.isEmpty())
        {
            return "No hay nada que mostrar";
        }
        else
        {
            Iterator<Map.Entry<Integer,ArrayList<T>>>iterador= listadoHabitaciones.entrySet().iterator();
            while (iterador.hasNext())
            {
                Map.Entry<Integer,ArrayList<T>> entrada = iterador.next();
                mensaje= mensaje.append("\n" + "Habitacion: " + entrada.getKey() + "\n");
                for (T elemento: entrada.getValue())
                {
                    if(entrada.getValue().equals(EstadoHabitacion.OCUPADA))
                    {
                        mensaje=mensaje.append(elemento.toString()).append("\n");
                    }
                }
            }
        }
        return mensaje.toString();
    }

    public String listarHabitacionesDisponibles()
    {
        StringBuilder mensaje=new StringBuilder();
        if(listadoHabitaciones.isEmpty())
        {
            return "No hay nada que mostrar";
        }
        else
        {
            Iterator<Map.Entry<Integer,ArrayList<T>>>iterador= listadoHabitaciones.entrySet().iterator();
            while (iterador.hasNext())
            {
                Map.Entry<Integer,ArrayList<T>> entrada = iterador.next();
                mensaje= mensaje.append("\n" + "Habitacion: " + entrada.getKey() + "\n");
                for (T elemento: entrada.getValue())
                {
                    if(entrada.getValue().equals(EstadoHabitacion.DISPONIBLE))
                    {
                        mensaje=mensaje.append(elemento.toString()).append("\n");
                    }
                }
            }
        }
        return mensaje.toString();
    }

    public String listarHabitacionesOcupadasMotivo(EstadoHabitacion estadoHabitacion)
    {
        StringBuilder mensaje=new StringBuilder();
        if(listadoHabitaciones.isEmpty())
        {
            return "No hay nada que mostrar";
        }
        else
        {
            Iterator<Map.Entry<Integer,ArrayList<T>>>iterador= listadoHabitaciones.entrySet().iterator();
            while (iterador.hasNext())
            {
                Map.Entry<Integer,ArrayList<T>> entrada = iterador.next();
                mensaje= mensaje.append("\n" + "Habitacion: " + entrada.getKey() + "\n");
                for (T elemento: entrada.getValue())
                {
                    if(entrada.getValue().equals(EstadoHabitacion.OCUPADA))
                    {
                        if(entrada.getValue().equals(estadoHabitacion))
                        {
                            mensaje=mensaje.append("").append(elemento.toString()).append("\n");
                        }
                    }
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

    public int buscar(int key , T elemento)
    {
        Reserva buscar = (Reserva) elemento;
        ArrayList <Reserva> reservas = (ArrayList<Reserva>) listadoHabitaciones.get(key);
        for (int i = 0 ; i < reservas.size() ; i++)
        {
            if (reservas.get(i).equals(buscar))
            {
                return i;
            }
        }
        return -1;
    }

    public Reserva buscarReservaPorPasajero(int idPasajero) {
        // Iterar sobre el mapa de habitaciones
        for (Map.Entry<Integer, ArrayList<T>> entry : listadoHabitaciones.entrySet()) {
            // Obtener la lista de reservas para esta habitación
            ArrayList<T> reservas = entry.getValue();

            // Buscar en la lista de reservas si alguna tiene el idPasajero igual al proporcionado
            for (T reserva : reservas) {
                if (reserva instanceof Reserva) {
                    Reserva reservaActual = (Reserva) reserva;
                    if (reservaActual.getIdPasajero() == idPasajero) {
                        // Si encontramos la reserva, la retornamos
                        return reservaActual;
                    }
                }
            }
        }
        // Si no se encuentra ninguna reserva para el ID de pasajero, retornamos null
        return null;
    }


    //consultar listado de de habitaciones actualmente ocupadas
}
