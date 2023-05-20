package co.edu.unbosque.parkea.model.dto;

import lombok.Getter;
import lombok.Setter;

public class TipoParqueaderoDTO {

    @Getter @Setter
    private int idTipoParq;

    @Getter @Setter
    private String descripcion;

    @Getter @Setter
    private String estado;

    public TipoParqueaderoDTO(int idTipoParq, String descripcion, String estado) {
        this.idTipoParq = idTipoParq;
        this.descripcion = descripcion;
        this.estado = estado;
    }
}
