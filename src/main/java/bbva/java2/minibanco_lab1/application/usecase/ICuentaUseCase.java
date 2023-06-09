package bbva.java2.minibanco_lab1.application.usecase;

import bbva.java2.minibanco_lab1.presentation.request.cuentaReq.CuentaCreateReq;
import bbva.java2.minibanco_lab1.presentation.response.cuentaResp.CuentaResponse;

public interface ICuentaUseCase {
    CuentaResponse guardarCuenta(CuentaCreateReq cuentaReq);
}
