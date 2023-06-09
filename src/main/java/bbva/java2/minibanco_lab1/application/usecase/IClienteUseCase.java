package bbva.java2.minibanco_lab1.application.usecase;

import bbva.java2.minibanco_lab1.domain.model.Cliente;
import bbva.java2.minibanco_lab1.presentation.response.clienteResp.ClienteResponse;

import java.util.Optional;

public interface IClienteUseCase {
    ClienteResponse guardarCliente(Cliente cliente);

    Optional<Cliente> listarUnCliente(Long id);

    // es void hasta que se cree una clase response para el caso
    void agregarCuentaACliente(Long idCliente, Long idCuenta, boolean titular);
}
