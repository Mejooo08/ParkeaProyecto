package co.edu.unbosque.parkea.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@NamedQuery(name="Carro.findAll", query="SELECT c FROM Carro c")
public class Carro implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    @Column(name="id_carro")
    private int idCarro;

    //bi-directional many-to-one association to Usuario
    @ManyToOne
    @Getter @Setter @JoinColumn(name="id_usuario")
    private Usuario usuario;

    @Getter @Setter @Column(name = "placa")
    private String  placa;

    @Getter @Setter @Column(name = "modelo")
    private String  modelo;

    @Getter @Setter @Column(name = "estado")
    private String  estado;

    public Carro(){
    }
}
