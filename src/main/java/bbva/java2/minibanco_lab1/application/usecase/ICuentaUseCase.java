package bbva.java2.minibanco_lab1.application.usecase;

import bbva.java2.minibanco_lab1.domain.model.Cuenta;
import bbva.java2.minibanco_lab1.presentation.request.cuentaReq.CuentaAddCotitularReq;
import bbva.java2.minibanco_lab1.presentation.request.cuentaReq.CuentaCreateReq;
import bbva.java2.minibanco_lab1.presentation.response.cuentaResp.CuentaCreateResp;

import java.util.Optional;

public interface ICuentaUseCase {
    CuentaCreateResp guardarCuenta(CuentaCreateReq cuentaReq);

    CuentaCreateResp agregarCotitularAcuenta(CuentaAddCotitularReq addCotitularReq);

    Cuenta buscarCuentaPorId(Long id);
}
