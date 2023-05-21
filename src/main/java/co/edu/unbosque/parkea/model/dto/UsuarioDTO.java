package co.edu.unbosque.parkea.model.dto;

import co.edu.unbosque.parkea.model.Rol;
import lombok.Getter;
import lombok.Setter;

public class UsuarioDTO {

    @Getter @Setter
    private int idUsuario;

    @Getter @Setter
    private Rol rol;

    @Getter @Setter
    private String login;

    @Getter @Setter
    private String direccion;

    @Getter @Setter
    private String idDocumento;

    @Getter @Setter
    private String numeroDoc;

    @Getter @Setter
    private int puntosFidelizacion;

    @Getter @Setter
    private String tarjetaCredito;

    @Getter @Setter
    private int intentos;

    @Getter @Setter
    private String estado;

    public UsuarioDTO(int idUsuario, Rol rol, String login, String direccion, String idDocumento, String numeroDoc, int puntosFidelizacion, String tarjetaCredito, int intentos, String estado) {
        this.idUsuario = idUsuario;
        this.rol = rol;
        this.login = login;
        this.direccion = direccion;
        this.idDocumento = idDocumento;
        this.numeroDoc = numeroDoc;
        this.puntosFidelizacion = puntosFidelizacion;
        this.tarjetaCredito = tarjetaCredito;
        this.intentos = intentos;
        this.estado = estado;
    }

    public UsuarioDTO(){

    }
}
