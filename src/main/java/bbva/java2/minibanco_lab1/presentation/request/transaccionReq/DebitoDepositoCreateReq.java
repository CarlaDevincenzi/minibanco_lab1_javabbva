package bbva.java2.minibanco_lab1.presentation.request.transaccionReq;

import bbva.java2.minibanco_lab1.domain.enums.TipoTransaccionEnum;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
public class DebitoDepositoCreateReq {

    @NotNull(message = "Debe especificarse el tipo de transaccion")
    private TipoTransaccionEnum tipoTransaccion;

    private String userEmail;
    @NotNull(message = "Debe especificarse la cuenta origen")
    private Long cuentaOrigen;
    private String descripcion;
    @NotNull(message = "Debe especificarse el monto")
    private BigDecimal monto;

}
