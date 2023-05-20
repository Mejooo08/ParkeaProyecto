package co.edu.unbosque.parkea.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    @Column(name="id_usuario")
    private int idUsuario;

    @ManyToOne
    @Getter @Setter @JoinColumn(name="id_rol")
    private Rol rol;

    @Getter @Setter @Column(name = "login")
    private String login;

    @Getter @Setter @Column(name = "clave")
    private String clave;

    @Getter @Setter @Column(name = "direccion")
    private String direccion;

    @ManyToOne
    @Getter @Setter @JoinColumn(name = "id_documento")
    private TipoDocumento idDocumento;

    @Getter @Setter @Column(name = "numero_doc")
    private String numeroDoc;

    @Getter @Setter @Column(name = "puntos_fidelizacion")
    private int puntosFidelizacion;

    @Getter @Setter @Column(name = "tajeta_credito")
    private String tajetaCredito;

    @Getter @Setter @Column(name = "intentos")
    private int intentos;

    @Getter @Setter @Column(name = "estado")
    private String estado;

    //bi-directional many-to-one association to Telefono
    @OneToMany(mappedBy="usuario")
    @Getter @Setter
    private List<Telefono> telefonos;

    //bi-directional many-to-one association to Telefono
    @OneToMany(mappedBy="usuario")
    @Getter @Setter
    private List<ReservaCupo> reservas;

    //bi-directional many-to-one association to Telefono
    @OneToMany(mappedBy="usuario")
    @Getter @Setter
    private List<Auditoria> auditorias;

    //bi-directional many-to-one association to Telefono
    @OneToMany(mappedBy="usuario")
    @Getter @Setter
    private List<Carro> carros;

    public Usuario() {
    }
}
