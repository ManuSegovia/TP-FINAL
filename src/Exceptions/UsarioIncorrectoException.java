package Exceptions;

public class UsarioIncorrectoException extends RuntimeException {
    public UsarioIncorrectoException(String message) {
        super("Usuario incorrecto");
    }
}
