package bbva.java2.minibanco_lab1.presentation.mapper;

import bbva.java2.minibanco_lab1.application.repository.IClienteRepository;
import bbva.java2.minibanco_lab1.application.usecase.IClienteUseCase;
import bbva.java2.minibanco_lab1.domain.model.Cuenta;
import bbva.java2.minibanco_lab1.presentation.request.cuentaReq.CuentaCreateReq;
import bbva.java2.minibanco_lab1.presentation.response.cuentaResp.CuentaCreateResp;
import bbva.java2.minibanco_lab1.presentation.response.cuentaResp.CuentaSimpleResp;
import bbva.java2.minibanco_lab1.presentation.response.cuentaResp.CuentaTransaccionesResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CuentaPresentacionMapper {

    private final IClienteRepository clienteRepository;
    public Cuenta requestToDomain(CuentaCreateReq cuentaReq) {
        Cuenta cuenta = new Cuenta();
        cuenta.setMoneda(cuentaReq.getMoneda());

        return cuenta;
    }

    public CuentaCreateResp domainToResponse(Cuenta cuenta) {
        return new CuentaCreateResp(cuenta.getIdCuenta(),
                cuenta.getNumeroCuenta(),
                cuenta.getMoneda(),
                cuenta.getSaldo(),
                cuenta.getTitular(),
                cuenta.getCotitular() != null ? cuenta.getCotitular() : -1L);

    }

    public CuentaSimpleResp domainToSimpleResponse(Cuenta cuenta) {
        return new CuentaSimpleResp(cuenta.getNumeroCuenta(),
                                    cuenta.getMoneda(),
                                    cuenta.getSaldo());
    }

    public CuentaTransaccionesResp domainToCuentaTransaccionesResponse(Cuenta cuenta) {
        return new CuentaTransaccionesResp(this.domainToSimpleResponse(cuenta),
                cuenta.getHistorialTransacciones());
    }

}
