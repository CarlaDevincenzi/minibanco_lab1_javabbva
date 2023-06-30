package bbva.java2.minibanco_lab1.presentation.mapper;

import bbva.java2.minibanco_lab1.domain.model.Cliente;
import bbva.java2.minibanco_lab1.domain.model.Cuenta;
import bbva.java2.minibanco_lab1.presentation.request.clienteReq.ClienteCreateReq;
import bbva.java2.minibanco_lab1.presentation.response.clienteResp.ClienteCreateResp;
import bbva.java2.minibanco_lab1.presentation.response.clienteResp.ClienteCuentasResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ClientePresentacionMapper {

    private final CuentaPresentacionMapper cuentaMapper;
    public Cliente requestToDomain(ClienteCreateReq clienteReq) {
        return new Cliente(clienteReq.getNombre(),
                clienteReq.getApellido(),
                clienteReq.getDni(),
                clienteReq.getEmail(),
                clienteReq.getContrasenia(),
                clienteReq.getDomicilio(),
                clienteReq.getTelefono());
    }

    public ClienteCreateResp domainToCreateResponse(Cliente cliente) {
        return new ClienteCreateResp(cliente.getIdCliente(),
                                            cliente.getNombre(),
                                            cliente.getApellido(),
                                            cliente.getDni(),
                                            cliente.getEmail(),
                                            cliente.getDomicilio(),
                                            cliente.getTelefono() != null ? cliente.getTelefono() : "------");

    }

    public ClienteCuentasResp domainToClienteCuentasResponse(Cliente cliente, List<Cuenta> propias, List<Cuenta> cotituladas) {
        ClienteCuentasResp clienteCuentasResp = new ClienteCuentasResp();

        clienteCuentasResp.setClienteCreateResp(domainToCreateResponse(cliente));
        clienteCuentasResp.setCuentasPropias(propias
                .stream()
                .map(cta -> cuentaMapper.domainToSimpleResponse(cta))
                .toList());

        clienteCuentasResp.setCuentasCotituladas(cotituladas
                .stream()
                .map(cta -> cuentaMapper.domainToSimpleResponse(cta))
                .toList());

        return clienteCuentasResp;
    }
}
