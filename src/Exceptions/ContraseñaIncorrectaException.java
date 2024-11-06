package Exceptions;

//nuevo
public class ContraseñaIncorrectaException extends RuntimeException
{
    public ContraseñaIncorrectaException(String message)
    {
        super("Contraseña incorrecta");
    }
}
