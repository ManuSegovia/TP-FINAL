package Exceptions;

public class HabitacionReservadaException extends RuntimeException {
  public HabitacionReservadaException(String message) {
    super("Clases.Habitacion reservada");
  }
}
