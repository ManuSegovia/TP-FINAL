package Clases;

import java.util.ArrayList;

public class GestorDeDatos<T>
{
    private ArrayList<T>elementos;

    public GestorDeDatos()
    {
        this.elementos = new ArrayList<>();
    }

    public ArrayList<T> getElementos() {
        return elementos;
    }

    public void setElementos(ArrayList<T> elementos) {
        this.elementos = elementos;
    }

    public void agregar(T elemento)
    {
        elementos.add(elemento);
    }

    public void eliminar(T elemento)
    {
        elementos.remove(elemento);
    }

    public String listar()
    {
        StringBuilder mensaje=new StringBuilder();
        if(elementos.isEmpty())
        {
            return "No hay elementos que mostrar";
        }
        for(T elemento: elementos)
        {
            mensaje=mensaje.append(elemento).append("\n");
        }
        return mensaje.toString();
    }
}
