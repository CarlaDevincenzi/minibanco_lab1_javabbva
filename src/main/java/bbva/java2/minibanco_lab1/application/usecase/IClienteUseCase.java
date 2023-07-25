package bbva.java2.minibanco_lab1.application.usecase;

import bbva.java2.minibanco_lab1.presentation.request.clienteReq.ClienteCreateReq;
import bbva.java2.minibanco_lab1.presentation.request.clienteReq.ClienteUpdateReq;
import bbva.java2.minibanco_lab1.presentation.response.clienteResp.ClienteCreateResp;
import bbva.java2.minibanco_lab1.presentation.response.clienteResp.ClienteCuentasResp;

import java.util.List;

public interface IClienteUseCase {
    ClienteCreateResp guardarCliente(ClienteCreateReq clienteReq);

    ClienteCuentasResp listarUnClienteConCuentas(String dni);

    List<ClienteCuentasResp> listarClientesActivos();
    List<ClienteCreateResp> listarClientesInactivos();
    boolean esCuentaCliente(String userEmail, Long idCuenta);
    boolean esTitular(String userEmail, Long idCuenta);
    boolean esCotitular(String userEmail, Long idCuenta);

    String bajaCliente(String userEmail);

    ClienteCreateResp updateCliente(ClienteUpdateReq updateReq);
}
