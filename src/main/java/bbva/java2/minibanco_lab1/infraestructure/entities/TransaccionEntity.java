package bbva.java2.minibanco_lab1.infraestructure.entities;

import bbva.java2.minibanco_lab1.domain.enums.TipoTransaccionEnum;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transacciones")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransaccionEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID idTransaccion;

    @Enumerated(value = EnumType.STRING)
    private TipoTransaccionEnum tipoTransaccion;

    private UUID idCliente;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime diahoraTransaccion;

    @ManyToOne
    @JoinColumn(name = "cuenta_id")
    private CuentaEntity cuenta;

    private String descripcion;

    private BigDecimal monto;
}
