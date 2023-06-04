package co.edu.unbosque.parkea.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@NamedQuery(name="Telefono.findAll", query="SELECT t FROM Telefono t")
public class Telefono implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    @Column(name="id_telefono")
    private int idTelefono;

    @Getter @Setter @Column(name="num_telefono")
    private String numTelefono;

    //bi-directional many-to-one association to Usuario
    @ManyToOne
    @Getter @Setter @JoinColumn(name="id_usuario")
    private Usuario usuario;

    @Getter @Setter @Column(name = "estado")
    private String estado;

    /**
     * Este método se usa como constructor de la clase
     */
    public Telefono() {
    }
}
