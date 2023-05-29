package bbva.java2.minibanco_lab1.domain.model;

import bbva.java2.minibanco_lab1.domain.enums.TipoTransaccionEnum;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaccion {
    private UUID idTransaccion;
    private TipoTransaccionEnum tipoTransaccion;
    private UUID idCliente;
    private LocalDateTime diahoraTransaccion;
    private UUID cuentaOrigen;
    private UUID cuentaDestino;
    private BigDecimal monto;
}
