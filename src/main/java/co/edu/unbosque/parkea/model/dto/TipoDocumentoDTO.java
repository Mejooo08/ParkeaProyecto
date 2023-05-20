package co.edu.unbosque.parkea.model.dto;

import lombok.Getter;
import lombok.Setter;

public class TipoDocumentoDTO {

    @Getter @Setter
    private int idDocumento;

    @Getter @Setter
    private String descripcion;

    @Getter @Setter
    private String estado;

    public TipoDocumentoDTO(int idDocumento, String descripcion, String estado) {
        this.idDocumento = idDocumento;
        this.descripcion = descripcion;
        this.estado = estado;
    }
}
