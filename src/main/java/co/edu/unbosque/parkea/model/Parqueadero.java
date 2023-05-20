package co.edu.unbosque.parkea.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@NamedQuery(name="Parqueadero.findAll", query="SELECT p FROM Parqueadero p")
public class Parqueadero implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    @Column(name="id_parqueadero")
    private int idParqueadero;

    @ManyToOne
    @Getter @Setter @JoinColumn(name="id_tipo_parq")
    private TipoParqueadero idTipoParq;

    @Getter @Setter @Column(name = "ubicacion")
    private String ubicacion;

    @Getter @Setter @Column(name = "horario_atencion")
    private String horarioAtencion;

    @Getter @Setter @Column(name = "tarifa")
    private int tarifa;

    @Getter @Setter @Column(name = "fidelizacion")
    private String fidelizacion;

    @Getter @Setter @Column(name = "ciudad")
    private String ciudad;

    @Getter @Setter @Column(name = "cupos_totales")
    private int cuposTotales;

    @Getter @Setter @Column(name = "cupos_ocupados")
    private int cuposOcupados;

    @Getter @Setter @Column(name = "estado")
    private String estado;

    //bi-directional many-to-one association to ReservaCupo
    @OneToMany(mappedBy="idParqueadero")
    @Getter @Setter
    private List<ReservaCupo> reservas;

    public Parqueadero() {
    }
}
