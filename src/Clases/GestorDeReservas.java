package Clases;

import Enums.EstadoHabitacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GestorDeReservas <T>
{
    private Map<Integer, ArrayList<T>> listadoHabitaicones;

    public GestorDeReservas()
    {
        this.listadoHabitaicones =new HashMap<>();
    }

    public void agregar(int numeroHabitacion, T elemento)
    {
        if(!listadoHabitaicones.containsKey(numeroHabitacion))
        {
            listadoHabitaicones.put(numeroHabitacion,new ArrayList<>());
        }
        listadoHabitaicones.get(numeroHabitacion).add(elemento);
    }

    public void eliminar(int key)
    {
        listadoHabitaicones.remove(key);
    }

    public String listar()
    {
        StringBuilder mensaje=new StringBuilder();
        if(listadoHabitaicones.isEmpty())
        {
            return "No hay nada que mostrar";
        }
        else
        {
            Iterator<Map.Entry<Integer,ArrayList<T>>>iterador= listadoHabitaicones.entrySet().iterator();
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
        if(listadoHabitaicones.isEmpty())
        {
            return "No hay nada que mostrar";
        }
        else
        {
            Iterator<Map.Entry<Integer,ArrayList<T>>>iterador= listadoHabitaicones.entrySet().iterator();
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
        if(listadoHabitaicones.isEmpty())
        {
            return "No hay nada que mostrar";
        }
        else
        {
            Iterator<Map.Entry<Integer,ArrayList<T>>>iterador= listadoHabitaicones.entrySet().iterator();
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
        if(listadoHabitaicones.isEmpty())
        {
            return "No hay nada que mostrar";
        }
        else
        {
            Iterator<Map.Entry<Integer,ArrayList<T>>>iterador= listadoHabitaicones.entrySet().iterator();
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

    public boolean habitacionDisponibleFechas(int key,LocalDate inicioReserva, LocalDate finReserva)
    {
        if(listadoHabitaicones.get(key))
        {
            //  
        }
    }

    //agregar metodo buscar

    //consultar listado de de habitaciones actualmente ocupadas
}
