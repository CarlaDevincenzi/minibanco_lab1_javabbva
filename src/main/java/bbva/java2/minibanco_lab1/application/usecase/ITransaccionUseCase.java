package bbva.java2.minibanco_lab1.application.usecase;

import bbva.java2.minibanco_lab1.presentation.request.transaccionReq.DebitoDepositoCreateReq;
import bbva.java2.minibanco_lab1.presentation.request.transaccionReq.TransferenciaReq;
import bbva.java2.minibanco_lab1.presentation.response.transaccionResp.DebitoDepositoResp;
import bbva.java2.minibanco_lab1.presentation.response.transaccionResp.TransferenciaResp;

import java.math.BigDecimal;

public interface ITransaccionUseCase {
    DebitoDepositoResp deposito_debito_transaccion(DebitoDepositoCreateReq req);

    TransferenciaResp transferencia(TransferenciaReq req);
}
