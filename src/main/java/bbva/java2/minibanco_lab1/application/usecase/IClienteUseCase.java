package bbva.java2.minibanco_lab1.application.usecase;

import bbva.java2.minibanco_lab1.domain.model.Cliente;
import bbva.java2.minibanco_lab1.presentation.request.clienteReq.ClienteCreateReq;
import bbva.java2.minibanco_lab1.presentation.response.clienteResp.ClienteCreateResp;
import bbva.java2.minibanco_lab1.presentation.response.clienteResp.ClienteCuentasResp;

import java.util.Optional;

public interface IClienteUseCase {
    ClienteCreateResp guardarCliente(ClienteCreateReq clienteReq);

    ClienteCuentasResp listarUnClienteConCuentas(String dni);

}
