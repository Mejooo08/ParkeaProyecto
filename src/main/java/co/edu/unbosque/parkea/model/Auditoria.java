package co.edu.unbosque.parkea.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@NamedQuery(name="Auditoria.findAll", query="SELECT a FROM Auditoria a")
public class Auditoria implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    @Column(name="id_informe")

    private int idInforme;

    //bi-directional many-to-one association to Usuario
    @ManyToOne
    @Getter @Setter @JoinColumn(name="id_usuario")
    private Usuario usuario;

    @Getter @Setter @Column(name = "fecha_hora")
    private String fechaHora;

    @Getter @Setter @Column(name = "evento")
    private String  evento;

    @Getter @Setter @Column(name = "tabla")
    private String  tabla;

    @Getter @Setter @Column(name = "ip_usuario")
    private String  ipUsuario;

    /**
     * Este método se usa como constructor de la clase
     */
    public Auditoria() {
    }
}
