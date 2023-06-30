package bbva.java2.minibanco_lab1.infraestructure.entities;

import lombok.*;

import javax.persistence.*;
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

    @OneToMany(mappedBy = "titular", fetch = FetchType.LAZY)
    private List<CuentaEntity> cuentasPropias;

    @OneToMany(mappedBy = "cotitular", fetch = FetchType.LAZY)
    private List<CuentaEntity> cuentasCotituladas;

}
