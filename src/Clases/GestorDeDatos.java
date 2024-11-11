package Clases;

import Interfaces.JSONable;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

//lo cambie de array a map todo
public class GestorDeDatos<T extends JSONable> implements JSONable
{
    private Map<Integer,T> elementos;

    public GestorDeDatos()
    {
        this.elementos=new HashMap<>();
    }

    public void agregar(int key,T elemento)
    {
        elementos.put(key,elemento);
    }

    public void eliminar(int key)
    {
        elementos.remove(key);
    }

    public String listar() {
        StringBuilder mensaje = new StringBuilder();
        if (elementos.isEmpty()) {
            return "No hay nada que mostrar";
        } else {
            Iterator<Map.Entry<Integer, T>> iterador = elementos.entrySet().iterator();
            while (iterador.hasNext()) {
                // Acceder solo al valor de la entrada, no a la clave
                mensaje.append(iterador.next().getValue()).append("\n");
            }
        }

        return mensaje.toString();
    }


    //nuevo
    public T buscar(int key)
    {
        return elementos.get(key);
    }

    public boolean buscarBoolean(int key)
    {
        return elementos.containsKey(key);
    }

    public Map<Integer, T> getElementos() {
        return elementos;
    }

    public void setElementos(Map<Integer, T> elementos) {
        this.elementos = elementos;
    }

    public boolean isEmpty()
    {
        return elementos.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GestorDeDatos<?> that = (GestorDeDatos<?>) o;
        return Objects.equals(elementos, that.elementos);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(elementos);
    }

    @Override
    public String toString() {
        return "GestorDeDatos{" +
                "elementos=" + elementos +
                '}';
    }

    @Override
    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        // Itera sobre el mapa y convierte cada elemento a JSON
        for (Map.Entry<Integer, T> entry : elementos.entrySet()) {
            JSONObject elementJson = entry.getValue().toJSON();  // Convierte el objeto a JSON
            elementJson.put("id", entry.getKey());  // Añade el ID al objeto JSON
            jsonArray.put(elementJson);  // Agrega el objeto JSON al array
        }

        jsonObject.put("elementos", jsonArray);  // Agrega el array de elementos al objeto JSON
        return jsonObject;
    }
}
