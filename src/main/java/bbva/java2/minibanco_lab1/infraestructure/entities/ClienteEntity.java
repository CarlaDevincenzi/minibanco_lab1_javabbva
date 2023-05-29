package bbva.java2.minibanco_lab1.infraestructure.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "clientes")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteEntity {
    @Id
    private UUID idCliente;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @NotNull
    private String dni;

    @NotNull
    private String email;
    private String domicilio; // crear clase Domicilio
    private String telefono; // crear clase Telefono (caracteristica, prefijo, sufijo) ?

    @OneToMany
    private List<CuentaEntity> cuentasPropias;
    @ManyToMany
    private List<CuentaEntity> cuentasCotituladas;

}
