package bbva.java2.minibanco_lab1.application.usecase;

import bbva.java2.minibanco_lab1.presentation.request.transaccionReq.DebitoDepositoCreateReq;
import bbva.java2.minibanco_lab1.presentation.response.transaccionResp.DebitoDepositoResp;

import java.math.BigDecimal;

public interface ITransaccionUseCase {
    DebitoDepositoResp debito(DebitoDepositoCreateReq req);
    DebitoDepositoResp deposito_debito_transaccion(DebitoDepositoCreateReq req);
}
