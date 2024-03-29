package bbva.java2.minibanco_lab1.infraestructure.entities;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clientes")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private String dni;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String contrasenia;

    @Column(nullable = false)
    private String domicilio;

    private String telefono;

    @OneToMany(mappedBy = "titular", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    //@OneToMany(mappedBy = "titular", fetch = FetchType.LAZY)
    private List<CuentaEntity> cuentasPropias;

    //@OneToMany(mappedBy = "cotitular", fetch = FetchType.LAZY)
    @OneToMany(mappedBy = "cotitular", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<CuentaEntity> cuentasCotituladas;

    @Column(nullable = false)
    private boolean alta;
}
