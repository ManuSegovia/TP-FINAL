package Usuarios;

import org.json.JSONObject;

public class Pasajero
{
    private String nombre;
    private String apellido;
    private String dni;
    private String origen;
    private String domicilioOrigen;

    public Pasajero(String nombre, String apellido, String dni, String origen, String domicilioOrigen) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.origen = origen;
        this.domicilioOrigen = domicilioOrigen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDomicilioOrigen() {
        return domicilioOrigen;
    }

    public void setDomicilioOrigen(String domicilioOrigen) {
        this.domicilioOrigen = domicilioOrigen;
    }

    @Override
    public String toString() {
        return "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", origen='" + origen + '\'' +
                ", domicilioOrigen='" + domicilioOrigen + '\'' +
                '}';
    }
    public JSONObject toJSON ()
    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("nombre", this.nombre);
        jsonObject.put("apellido", this.apellido);
        jsonObject.put("dni", this.dni);
        jsonObject.put("origen", this.origen);
        jsonObject.put("domicilioOrigen", this.domicilioOrigen);
        return jsonObject;
    }

    public static Pasajero fromJson(JSONObject json) {
        String nombre = json.getString("nombre");
        String apellido = json.getString("apellido");
        String dni = json.getString("dni");
        String origen = json.getString("origen");
        String domicilioOrigen = json.getString("domicilioOrigen");

        return new Pasajero(nombre, apellido, dni, origen, domicilioOrigen);
    }

}
