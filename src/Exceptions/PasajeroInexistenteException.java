package Exceptions;

public class PasajeroInexistenteException extends RuntimeException {
    public PasajeroInexistenteException() {
        super("Clases.Pasajero inexistente");
    }
}
