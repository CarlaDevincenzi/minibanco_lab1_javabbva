package bbva.java2.minibanco_lab1.presentation.response.clienteResp;

import bbva.java2.minibanco_lab1.presentation.response.cuentaResp.CuentaSimpleResp;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ClienteCuentasResp {
    private ClienteCreateResp clienteCreateResp;
    private List<CuentaSimpleResp> cuentasPropias;
    private List<CuentaSimpleResp> cuentasCotituladas;
}
