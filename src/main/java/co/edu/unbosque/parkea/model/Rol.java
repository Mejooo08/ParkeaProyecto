package co.edu.unbosque.parkea.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the rol database table.
 *
 */
@Entity
@NamedQuery(name="Rol.findAll", query="SELECT r FROM Rol r")
public class Rol implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name="id_rol")
    private int idRol;

    @Getter @Setter @Column(name="tipo_rol")
    private String tipoRol;

    @Getter @Setter @Column(name = "estado")
    private String estado;

    //bi-directional many-to-one association to Usuario
    @Getter @Setter @OneToMany(mappedBy="rol")
    private List<Usuario> usuarios;

    public Rol() {
    }
}
