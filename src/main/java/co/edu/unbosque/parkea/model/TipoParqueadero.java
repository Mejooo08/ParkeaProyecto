package co.edu.unbosque.parkea.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="tipo_parqueadero")
@NamedQuery(name="TipoParqueadero.findAll", query="SELECT p FROM TipoParqueadero p")
public class TipoParqueadero implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    @Column(name="id_tipo_parq")
    private int idTipoParq;

    @Getter @Setter @Column(name="descripcion")
    private String descripcion;

    @Getter @Setter @Column(name = "estado")
    private String estado;

    //bi-directional many-to-one association to Usuario
    @Getter @Setter @OneToMany(mappedBy="idTipoParq")
    private List<Parqueadero> parqueaderos;

    /**
     * Este método se usa como constructor de la clase
     */
    public TipoParqueadero() {
    }
}

