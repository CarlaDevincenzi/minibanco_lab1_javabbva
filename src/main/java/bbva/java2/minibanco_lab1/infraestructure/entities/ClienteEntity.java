package bbva.java2.minibanco_lab1.infraestructure.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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
    private String domicilio;

    private String telefono;

    @OneToMany(mappedBy = "titular")
    private List<CuentaEntity> cuentasPropias;

    @OneToMany(mappedBy = "cotitular")
    private List<CuentaEntity> cuentasCotituladas;

}
