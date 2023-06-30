package bbva.java2.minibanco_lab1.presentation.request.transaccionReq;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class TransferenciaReq {
    @NotNull(message = "Debe especificarse la cuenta de origen")
    private DebitoDepositoCreateReq origenTr;
    @NotNull(message = "Debe especificarse la cuenta destino")
    private DebitoDepositoCreateReq destinoTr;
}
