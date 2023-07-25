package bbva.java2.minibanco_lab1.presentation.response.transaccionResp;

import bbva.java2.minibanco_lab1.domain.enums.TipoTransaccionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class DebitoDepositoResp {
    private Long idTransaccion;
    private TipoTransaccionEnum tipoTransaccion;
    private String userEmail;
    private LocalDateTime diahoraTransaccion;
    private Long cuentaOrigen;
    private String descripcion;
    private BigDecimal monto;
}
