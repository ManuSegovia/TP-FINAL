package Exceptions;

public class ReservaInexistenteException extends RuntimeException {
    public ReservaInexistenteException(String message) {
        super(message);
    }
}
