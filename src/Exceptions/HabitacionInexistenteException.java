package Exceptions;

public class HabitacionInexistenteException extends RuntimeException {
    public HabitacionInexistenteException() {
        super("Habitacion Inexistente");
    }
}
