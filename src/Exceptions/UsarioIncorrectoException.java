package Exceptions;

public class UsarioIncorrectoException extends RuntimeException {
    public UsarioIncorrectoException(String message) {
        super("Clases.Usuario incorrecto");
    }
}
