package bbva.java2.minibanco_lab1.infraestructure.entities;

import bbva.java2.minibanco_lab1.domain.enums.MonedaEnum;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "cuentas")
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CuentaEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID numeroCuenta;

    @Column(nullable = false)
    private BigDecimal saldo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MonedaEnum moneda;

    @ManyToOne
    @JoinColumn(name = "titular_id")
    private ClienteEntity titular;

    @ManyToOne
    @JoinColumn(name = "cotitular_id")
    private ClienteEntity cotitular;

    @OneToMany(mappedBy = "cuenta", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TransaccionEntity> historialTransacciones;

}
