package co.edu.unbosque.parkea.model.dto;

import lombok.Getter;
import lombok.Setter;

public class TelefonoDTO {

    @Getter @Setter
    private int idTelefono;

    @Getter @Setter
    private String numTelefono;

    @Getter @Setter
    private String estado;

    @Getter @Setter
    private String usuario;

    public TelefonoDTO(int idTelefono, String numTelefono, String estado, String usuario) {
        this.idTelefono = idTelefono;
        this.numTelefono = numTelefono;
        this.estado = estado;
        this.usuario = usuario;
    }
}
