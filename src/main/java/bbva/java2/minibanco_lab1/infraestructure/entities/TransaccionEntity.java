package bbva.java2.minibanco_lab1.infraestructure.entities;

import bbva.java2.minibanco_lab1.domain.enums.TipoTransaccionEnum;
import javax.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transacciones")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransaccionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTransaccion;

    @Enumerated(value = EnumType.STRING)
    private TipoTransaccionEnum tipoTransaccion;

    @Column(nullable = false)
    private Long idCliente;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime diahoraTransaccion;

    @ManyToOne
    @JoinColumn(name = "cuenta_id")
    private CuentaEntity cuentaOrigen;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private BigDecimal monto;

    public TransaccionEntity(TipoTransaccionEnum tipoTransaccion, Long idCliente, LocalDateTime diahoraTransaccion,
                             CuentaEntity cuenta, String descripcion, BigDecimal monto) {
        this.tipoTransaccion = tipoTransaccion;
        this.idCliente = idCliente;
        this.diahoraTransaccion = diahoraTransaccion;
        this.cuentaOrigen = cuenta;
        this.descripcion = descripcion;
        this.monto = monto;
    }
}
