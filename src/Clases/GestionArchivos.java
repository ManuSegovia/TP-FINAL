package Clases;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;

public class GestionArchivos
{
    public static void crearArchivoJSON (String nombre) throws IOException
    {
        File arch = new File(nombre);
        if (arch.exists())
        {
            throw new IOException("El archivo ya existe");
        }
        try (BufferedWriter escribir = new BufferedWriter(new FileWriter(nombre)))
        {
            escribir.close();

        }catch (IOException e)
        {
            throw new IOException("El archivo " + nombre + " no se pudo crear");
        }
    }

    public static void sobreescribirarchJSON(String nombre, JSONObject añadir) throws IOException {
        File archivo = new File(nombre);
        if (!archivo.exists()) {
            throw new IOException("El archivo no existe");
        } else {
            try (BufferedWriter escribir = new BufferedWriter(new FileWriter(nombre))) {
                escribir.write(añadir.toString());
                escribir.close();
            } catch (IOException e) {
                throw new IOException("Ocurrio una excepcion en la escritura del archivo");
            }
        }
    }
    public static void sobreescribirarchJSON (String nombre, JSONArray añadir) throws IOException
    {
        File archivo = new File(nombre);
        if (!archivo.exists())
        {
            throw new IOException("El archivo no existe");
        }
        else
        {
            try (BufferedWriter escribir = new BufferedWriter(new FileWriter(nombre)))
            {
                escribir.write(añadir.toString());
                escribir.close();
            }catch (IOException e)
            {
                throw new IOException("Ocurrio una excepcion en la escritura del archivo");
            }
        }
    }

    public static String leerarchivoJSON (String nombre) throws IOException
    {
        File archivo = new File(nombre);

        if (!archivo.exists())
        {
            throw new IOException("El archivo no existe");

        }else
        {
            try (BufferedReader leer = new BufferedReader(new FileReader(nombre)))
            {
                String linea = leer.readLine();
                leer.close();
                return linea;
            }
            catch (IOException e)
            {
                throw new IOException("Ocurrio un error al leer el archivo " + nombre);
            }
        }
    }
}
