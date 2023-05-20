package co.edu.unbosque.parkea.model.dto;

import lombok.Getter;
import lombok.Setter;

public class CarroDTO {

    @Getter @Setter
    private int idCarro;

    @Getter @Setter
    private String usuario;

    @Getter @Setter
    private String placa;

    @Getter @Setter
    private String modelo;

    public CarroDTO(int idCarro, String usuario, String placa, String modelo) {
        this.idCarro = idCarro;
        this.usuario = usuario;
        this.placa = placa;
        this.modelo = modelo;
    }
}
