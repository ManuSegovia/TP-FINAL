package Enums;

public enum EstadoReserva {
    CHECKIN("check-in"),
    CHECKOUT("check-out"),
    PENDIENTE("pendiente");
    private String estado_Habitacion;

    EstadoReserva(String estado_Habitacion) {
        this.estado_Habitacion = estado_Habitacion;
    }

    public String getEstado_Habitacion() {
        return estado_Habitacion;
    }

    public void setEstado_Habitacion(String estado_Habitacion) {
        this.estado_Habitacion = estado_Habitacion;
    }
}
