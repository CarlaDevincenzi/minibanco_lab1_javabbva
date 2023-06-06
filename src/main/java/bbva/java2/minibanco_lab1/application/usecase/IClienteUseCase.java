package bbva.java2.minibanco_lab1.application.usecase;

import bbva.java2.minibanco_lab1.presentation.request.clienteReq.ClienteCreateReq;
import bbva.java2.minibanco_lab1.presentation.response.clienteResp.ClienteResponse;

public interface IClienteUseCase {
    ClienteResponse guardarCliente(ClienteCreateReq clienteCreateReq);
}
