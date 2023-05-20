package co.edu.unbosque.parkea.model.dto;

import lombok.Getter;
import lombok.Setter;

public class ParqueaderoDTO {

    @Getter @Setter
    private int idParqueadero;

    @Getter @Setter
    private String idTipoParq;

    @Getter @Setter
    private String ubicacion;

    @Getter @Setter
    private String horarioAtencion;

    @Getter @Setter
    private int tarifa;

    @Getter @Setter
    private String fidelizacion;

    @Getter @Setter
    private String ciudad;

    @Getter @Setter
    private int cuposTotales;

    @Getter @Setter
    private int cuposOcupados;

    @Getter @Setter
    private String estado;

    public ParqueaderoDTO(int idParqueadero, String idTipoParq, String ubicacion, String horarioAtencion, int tarifa, String fidelizacion, String ciudad, int cuposTotales, int cuposOcupados, String estado) {
        this.idParqueadero = idParqueadero;
        this.idTipoParq = idTipoParq;
        this.ubicacion = ubicacion;
        this.horarioAtencion = horarioAtencion;
        this.tarifa = tarifa;
        this.fidelizacion = fidelizacion;
        this.ciudad = ciudad;
        this.cuposTotales = cuposTotales;
        this.cuposOcupados = cuposOcupados;
        this.estado = estado;
    }
}
