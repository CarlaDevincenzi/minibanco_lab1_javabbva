package bbva.java2.minibanco_lab1.domain.model;

import bbva.java2.minibanco_lab1.domain.enums.TipoTransaccionEnum;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaccion {
    private Long idTransaccion;
    private TipoTransaccionEnum tipoTransaccion;
    private Long idCliente;
    private LocalDateTime diahoraTransaccion;
    private Long cuentaOrigen;
    private String descripcion;
    private BigDecimal monto;

    public Transaccion(TipoTransaccionEnum tipoTransaccion, Long idCliente, Long cuentaOrigen, String descripcion, BigDecimal monto) {
        this.tipoTransaccion = tipoTransaccion;
        this.idCliente = idCliente;
        this.cuentaOrigen = cuentaOrigen;
        this.descripcion = descripcion;
        this.monto = monto;
    }
}
