package co.edu.unbosque.parkea.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="tipo_documento")
@NamedQuery(name="TipoDocumento.findAll", query="SELECT d FROM TipoDocumento d")
public class TipoDocumento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name="id_documento")
    private int idDocumento;

    @Getter @Setter @Column(name="descripcion")
    private String descripcion;

    @Getter @Setter @Column(name = "estado")
    private String estado;

    //bi-directional many-to-one association to Usuario
    @Getter @Setter @OneToMany(mappedBy="idDocumento")
    private List<Usuario> usuarios;

    /**
     * Este método se usa como constructor de la clase
     */
    public TipoDocumento() {
    }
}
