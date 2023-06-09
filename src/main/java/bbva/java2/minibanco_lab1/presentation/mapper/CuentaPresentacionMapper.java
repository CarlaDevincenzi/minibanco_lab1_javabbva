package bbva.java2.minibanco_lab1.presentation.mapper;

import bbva.java2.minibanco_lab1.domain.model.Cuenta;
import bbva.java2.minibanco_lab1.presentation.request.cuentaReq.CuentaCreateReq;
import bbva.java2.minibanco_lab1.presentation.response.cuentaResp.CuentaResponse;
import org.springframework.stereotype.Component;

@Component
public class CuentaPresentacionMapper {

    public Cuenta requestToDomain(CuentaCreateReq cuentaReq) {
        Cuenta cuenta = new Cuenta();
        cuenta.setMoneda(cuentaReq.getMoneda());
        cuenta.setTitular(cuentaReq.getTitular());
        cuenta.setCotitular(cuentaReq.getCotitular());

        return cuenta;
    }

    public CuentaResponse domainToResponse(Cuenta cuenta) {
        CuentaResponse cuentaResponse = new CuentaResponse();
        cuentaResponse.setIdCuenta(cuenta.getIdCuenta());
        cuentaResponse.setNumeroCuenta(cuenta.getNumeroCuenta());
        cuentaResponse.setMoneda(cuenta.getMoneda());
        cuentaResponse.setSaldo(cuenta.getSaldo());
        cuentaResponse.setTitular(cuenta.getTitular());
        cuentaResponse.setCotitular(cuenta.getCotitular() != null ? cuenta.getCotitular() : 0L);

        return cuentaResponse;
    }

}
