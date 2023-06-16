package bbva.java2.minibanco_lab1.presentation.request.transaccionReq;

import bbva.java2.minibanco_lab1.domain.enums.TipoTransaccionEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class DebitoDepositoCreateReq {

    @NotNull(message = "Debe especificarse el tipo de transaccion")
    private TipoTransaccionEnum tipoTransaccion;
    @NotNull(message = "Debe especificarse un cliente")
    private Long idCliente;
    @NotNull(message = "Debe especificarse la cuenta origen")
    private Long cuentaOrigen;
    private String descripcion;
    @NotNull(message = "Debe especificarse el monto")
    private BigDecimal monto;

}
