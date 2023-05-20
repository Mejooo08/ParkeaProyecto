package co.edu.unbosque.parkea.model.dto;

import lombok.Getter;
import lombok.Setter;

public class ReservaCupoDTO {

    @Getter @Setter
    private int idFactura;

    @Getter @Setter
    private String horaInicio;

    @Getter @Setter
    private String horaFinal;

    @Getter @Setter
    private String usuario;

    @Getter @Setter
    private String idParqueadero;

    @Getter @Setter
    private String placaCarro;

    public ReservaCupoDTO(int idFactura, String horaInicio, String horaFinal, String usuario, String idParqueadero, String placaCarro) {
        this.idFactura = idFactura;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
        this.usuario = usuario;
        this.idParqueadero = idParqueadero;
        this.placaCarro = placaCarro;
    }
}
