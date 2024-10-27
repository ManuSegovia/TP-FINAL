package Clases;

import Enums.TipoUsuario;
import Exceptions.GestionUsuarios;
import Usuarios.Administrador;
import Usuarios.Conserje;
import Usuarios.Pasajero;
import Usuarios.Usuario;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class Hotel {
    private String nombre;
    private String direccion;
    private GestorDeDatos <Habitacion> habitaciones = new GestorDeDatos<>();
    private GestorDeDatos <Usuario> usuarios = new GestorDeDatos<>();
    private GestorDeDatos <Reserva> reservas = new GestorDeDatos<>();
    private GestorDeDatos <Pasajero> pasajeros = new GestorDeDatos<>();

    public Hotel(String nombre, String direccion) throws IOException{
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public GestorDeDatos<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(GestorDeDatos<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }

    public GestorDeDatos<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(GestorDeDatos<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public GestorDeDatos<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(GestorDeDatos<Reserva> reservas) {
        this.reservas = reservas;
    }

    public GestorDeDatos<Pasajero> getPasajeros() {
        return pasajeros;
    }

    public void setPasajeros(GestorDeDatos<Pasajero> pasajeros) {
        this.pasajeros = pasajeros;
    }

    public JSONObject toJSON ()
    {
        JSONObject json = new JSONObject();
        json.put("nombreHotel", nombre);
        json.put("direccionHotel", direccion);
        return json;
    }

    // METODO QUE BUSCAR UN USUARIO, LA POSICION EN EL ARRAYLIST Y COMPRUEBA SU EXISTENCIA
    public int buscarUsuario(Usuario buscar) {
        if (usuarios.getElementos().isEmpty()) {
            return -1;
        } else {
            for (int i = 0; i < usuarios.getElementos().size(); i++) {
                if (usuarios.getElementos().get(i).getDni() == buscar.getDni()) {
                    return i;
                }
            }
        }
        return -1;
    }

    // AÑADIR USUARIO
    public String agregarUsuario(Usuario añadir) throws IOException, GestionUsuarios {

        if (buscarUsuario(añadir) == -1) {
            usuarios.agregar(añadir);
            sobreescribirArchivosJSON();
            return "Se añadio correctamente el " + añadir.getTipoUsuario() + " " + añadir.getNombre();
        } else {
            throw new GestionUsuarios("El usuario " + añadir.getNombre() + " ya se encuentra registrado");
        }
    }


    private static ArrayList <Usuario> fromUsuariosJSON() throws IOException {
        ArrayList <Usuario> usuarioAux = new ArrayList<>();
        String usuariosJSONARRAY = GestionArchivos.leerarchivoJSON("usuarios");
        JSONArray jsonArray = new JSONArray(usuariosJSONARRAY);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonUsuario = jsonArray.getJSONObject(i);

            TipoUsuario tipoUsuario = TipoUsuario.valueOf(jsonUsuario.getString("tipoUsuario"));

            Usuario usuario;
            if (tipoUsuario == TipoUsuario.ADMINISTRADOR) {

                usuario = Administrador.fromJson(jsonUsuario);
            } else {
                usuario = Conserje.fromJson(jsonUsuario);
            }
            usuarioAux.add(usuario);
        }
        return usuarioAux;
    }
//METODOS PARA RECUPERAR INFORMACION DEL HOTEL (EN ARCHIVOS JSON)--------------------------------------------
    private static ArrayList<Pasajero> fromPasajerosJSON() throws IOException{
        ArrayList <Pasajero> pasajeroAux = new ArrayList<>();
        JSONArray pasajerosJSON = new JSONArray(GestionArchivos.leerarchivoJSON("Pasajeros"));
        for (int i = 0; i < pasajerosJSON.length(); i++) {
            JSONObject jsonObject = pasajerosJSON.getJSONObject(i);
            pasajeroAux.add(Pasajero.fromJson(jsonObject));
        }
        return pasajeroAux;
    }

    private static ArrayList<Habitacion> fromHabitacionesJSON() throws IOException {
        ArrayList <Habitacion> habitacionesAux = new ArrayList<>();
        JSONArray habitacionesJSON = new JSONArray(GestionArchivos.leerarchivoJSON("Habitaciones"));
        for (int i = 0; i < habitacionesJSON.length(); i++) {
            JSONObject jsonObject = habitacionesJSON.getJSONObject(i);
            habitacionesAux.add(Habitacion.fromJSON(jsonObject));
        }
        return habitacionesAux;
    }

    private static ArrayList <Reserva> fromReservasJSON() throws IOException {
       ArrayList <Reserva> reservasAux = new ArrayList<>();
        JSONArray reservasJSON = new JSONArray(GestionArchivos.leerarchivoJSON("Reservas"));
        for (int i = 0; i < reservasJSON.length(); i++) {
            JSONObject jsonObject = reservasJSON.getJSONObject(i);
            reservasAux.add(Reserva.fromJSON(jsonObject));
        }
        return reservasAux;
    }

    public static Hotel fromHotelJSON () throws IOException
    {
        String contendio = GestionArchivos.leerarchivoJSON("hotel");
        JSONObject hotelJSON = new JSONObject(contendio);
        String nombreHotel = hotelJSON.getString("nombreHotel");
        String direccion = hotelJSON.getString("direccionHotel");
        Hotel hotelNuevo = new Hotel(nombreHotel, direccion);
        hotelNuevo.habitaciones.getElementos().addAll(Hotel.fromHabitacionesJSON());
        hotelNuevo.pasajeros.getElementos().addAll(Hotel.fromPasajerosJSON());
        hotelNuevo.reservas.getElementos().addAll(Hotel.fromReservasJSON());
        hotelNuevo.usuarios.getElementos().addAll(Hotel.fromUsuariosJSON());
        return hotelNuevo;
    }
//-----------------------------------------------------------------------------------------------------------------
    public void sobreescribirArchivosJSON () throws IOException
    {
        JSONObject hotelJSON = toJSON();
        GestionArchivos.sobreescribirarchJSON("hotel", hotelJSON);
        JSONArray habitacionesJSON = new JSONArray();
        for (int i = 0 ; i < habitaciones.getElementos().size() ; i++)
        {
            JSONObject habitacionJSON;
            habitacionJSON = habitaciones.getElementos().get(i).toJSON();
            habitacionesJSON.put(habitacionJSON);
        }
        GestionArchivos.sobreescribirarchJSON("habitaciones", habitacionesJSON);
        JSONArray usuariosJSON = new JSONArray();
        for (int i = 0 ; i < usuarios.getElementos().size() ; i++)
        {
            JSONObject usuarioJSON;
            usuarioJSON = usuarios.getElementos().get(i).toJson();
            usuariosJSON.put(usuarioJSON);
        }
        GestionArchivos.sobreescribirarchJSON("usuarios", usuariosJSON);
        JSONArray reservasJSON = new JSONArray();
        for (int i = 0 ; i < reservas.getElementos().size() ; i++)
        {
            JSONObject reservaJSON;
            reservaJSON = reservas.getElementos().get(i).toJSON();
            reservasJSON.put(reservaJSON);
        }
        GestionArchivos.sobreescribirarchJSON("reservas", reservasJSON);
        JSONArray pasajerosJSON = new JSONArray();
        for (int i = 0 ; i < reservas.getElementos().size() ; i++)
        {
            JSONObject pasajeroJSON;
            pasajeroJSON = pasajeros.getElementos().get(i).toJSON();
            pasajerosJSON.put(pasajeroJSON);
        }
        GestionArchivos.sobreescribirarchJSON("pasajeros", pasajerosJSON);
    }

}
