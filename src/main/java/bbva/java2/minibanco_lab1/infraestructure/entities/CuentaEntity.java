package bbva.java2.minibanco_lab1.infraestructure.entities;

import bbva.java2.minibanco_lab1.domain.enums.MonedaEnum;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "cuentas")
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CuentaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCuenta;

    @Column(nullable = false)
    private String numeroCuenta;

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

    //@OneToMany(mappedBy = "cuentaOrigen", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @OneToMany(mappedBy = "cuentaOrigen", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<TransaccionEntity> historialTransacciones;

    @Column(nullable = false)
    private boolean alta;

}
