package co.edu.unbosque.parkea.model.dto;

import lombok.Getter;
import lombok.Setter;

public class AuditoriaDTO {

    @Getter @Setter
    private int idInforme;

    @Getter @Setter
    private String usuario;

    @Getter @Setter
    private String fechaHora;

    @Getter @Setter
    private String evento;

    @Getter @Setter
    private String tabla;

    @Getter @Setter
    private String ipUsuario;

    public AuditoriaDTO(int idInforme, String usuario, String fechaHora, String evento, String tabla, String ipUsuario) {
        this.idInforme = idInforme;
        this.usuario = usuario;
        this.fechaHora = fechaHora;
        this.evento = evento;
        this.tabla = tabla;
        this.ipUsuario = ipUsuario;
    }
}
