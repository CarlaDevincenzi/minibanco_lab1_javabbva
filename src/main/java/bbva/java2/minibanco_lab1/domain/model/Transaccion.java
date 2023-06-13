package bbva.java2.minibanco_lab1.domain.model;

import bbva.java2.minibanco_lab1.domain.enums.TipoTransaccionEnum;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaccion {
    private Long idTransaccion;
    private TipoTransaccionEnum tipoTransaccion;
    private Long idCliente;
    private LocalDateTime diahoraTransaccion;
    private Long cuentaOrigen;
    private String cuentaDestino;
    private BigDecimal monto;
}
