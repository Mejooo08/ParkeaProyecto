package co.edu.unbosque.parkea.model.dto;

import lombok.Getter;
import lombok.Setter;

public class ReservaCupoDTO {

    @Getter @Setter
    private int idFactura;

    @Getter @Setter
    private String momentoReserva;

    @Getter @Setter
    private String horaIngreso;

    @Getter @Setter
    private String horaSalida;

    @Getter @Setter
    private String usuario;

    @Getter @Setter
    private String idParqueadero;

    @Getter @Setter
    private String placaCarro;

    @Getter @Setter
    private String estado;

    public ReservaCupoDTO(int idFactura, String momentoReserva, String horaIngreso, String horaSalida, String usuario, String idParqueadero, String placaCarro, String estado) {
        this.idFactura = idFactura;
        this.momentoReserva = momentoReserva;
        this.horaIngreso = horaIngreso;
        this.horaSalida = horaSalida;
        this.usuario = usuario;
        this.idParqueadero = idParqueadero;
        this.placaCarro = placaCarro;
        this.estado = estado;
    }
}
