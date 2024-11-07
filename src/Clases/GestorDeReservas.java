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

    public boolean agregar(int numeroHabitacion, T elemento)
    {
        Reserva cargar = (Reserva) elemento;
        if(!listadoHabitaciones.containsKey(numeroHabitacion))
        {
            listadoHabitaciones.put(numeroHabitacion,new ArrayList<>());
        }
        else
        {
            ArrayList<Reserva> reservasAux = (ArrayList<Reserva>) listadoHabitaciones.get(numeroHabitacion);

            // hacemos uso de un interador para recorrer y comprobar que no haya una reserva que impida nuestra reserva;
            Iterator<Reserva> iterator = reservasAux.iterator();
            while (iterator.hasNext()) {
                Reserva reserva = iterator.next();
                if ((cargar.getFechaInicio().isBefore(reserva.getFechaFin()) && cargar.getFechaFin().isAfter(reserva.getFechaInicio())) ||
                        (cargar.getFechaInicio().isEqual(reserva.getFechaInicio()) || cargar.getFechaFin().isEqual(reserva.getFechaFin()))) {
                    return false; // Si se solapan, no agregamos la reserva
                }
            }
        }
        listadoHabitaciones.get(numeroHabitacion).add((T) cargar);
        return true;
    }

    public boolean eliminar(int key, int id_Pasajero, LocalDate fechaInicio, LocalDate fecha_Fin) {
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
                    return true; // Terminamos y retornamos true
                }
            }
        }
        return false; // Si no se encontró la reserva
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
                mensaje= mensaje.append("\n" + "Clases.Habitacion: " + entrada.getKey() + "\n");
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
                mensaje= mensaje.append("\n" + "Clases.Habitacion: " + entrada.getKey() + "\n");
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
                mensaje= mensaje.append("\n" + "Clases.Habitacion: " + entrada.getKey() + "\n");
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
                mensaje= mensaje.append("\n" + "Clases.Habitacion: " + entrada.getKey() + "\n");
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


    //agregar metodo buscar

    //consultar listado de de habitaciones actualmente ocupadas
}
