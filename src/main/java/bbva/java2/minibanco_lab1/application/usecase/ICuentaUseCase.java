package bbva.java2.minibanco_lab1.application.usecase;

import bbva.java2.minibanco_lab1.domain.enums.TipoTransaccionEnum;
import bbva.java2.minibanco_lab1.domain.model.Cuenta;
import bbva.java2.minibanco_lab1.presentation.request.cuentaReq.CuentaAddCotitularReq;
import bbva.java2.minibanco_lab1.presentation.request.cuentaReq.CuentaCreateReq;
import bbva.java2.minibanco_lab1.presentation.response.cuentaResp.CuentaCreateResp;
import bbva.java2.minibanco_lab1.presentation.response.cuentaResp.CuentaTransaccionesResp;

import java.math.BigDecimal;
import java.util.Optional;

public interface ICuentaUseCase {
    CuentaCreateResp guardarCuenta(CuentaCreateReq cuentaReq);

    CuentaCreateResp agregarCotitularAcuenta(CuentaAddCotitularReq addCotitularReq);
    void updateMonto(Long idCuenta, BigDecimal monto, TipoTransaccionEnum tipoTr);
    Cuenta buscarCuentaPorId(Long id);

    CuentaTransaccionesResp mostrarUnaCuentaConTransacciones(Long id);
    boolean igualTipoMoneda(Long idOrigen, Long idDestino);

    String bajaCuenta(Long idCuenta);
}
