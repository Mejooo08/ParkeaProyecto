package co.edu.unbosque.parkea.model.dto;

import lombok.Getter;
import lombok.Setter;

public class UsuarioDTO {

    @Getter @Setter
    private int idUsuario;

    @Getter @Setter
    private String rol;

    @Getter @Setter
    private String login;

    @Getter @Setter
    private String direccion;

    @Getter @Setter
    private String idDocumento;

    @Getter @Setter
    private String puntosFidelizacion;

    @Getter @Setter
    private String tarjetaCredito;

    @Getter @Setter
    private String intentos;

    @Getter @Setter
    private String estado;

    public UsuarioDTO(int idUsuario, String rol, String login, String direccion, String idDocumento, String puntosFidelizacion, String tarjetaCredito, String intentos, String estado) {
        this.idUsuario = idUsuario;
        this.rol = rol;
        this.login = login;
        this.direccion = direccion;
        this.idDocumento = idDocumento;
        this.puntosFidelizacion = puntosFidelizacion;
        this.tarjetaCredito = tarjetaCredito;
        this.intentos = intentos;
        this.estado = estado;
    }
}
