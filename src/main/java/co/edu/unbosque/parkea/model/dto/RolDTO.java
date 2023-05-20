package co.edu.unbosque.parkea.model.dto;

import lombok.Getter;
import lombok.Setter;

public class RolDTO {

    @Getter @Setter
    private int idRol;

    @Getter @Setter
    private String tipoRol;

    @Getter @Setter
    private String estado;

    public RolDTO(int idRol, String tipoRol, String estado) {
        this.idRol = idRol;
        this.tipoRol = tipoRol;
        this.estado = estado;
    }
}
