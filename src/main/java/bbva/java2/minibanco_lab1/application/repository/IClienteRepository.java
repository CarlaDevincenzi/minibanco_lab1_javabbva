package bbva.java2.minibanco_lab1.application.repository;

import bbva.java2.minibanco_lab1.domain.model.Cliente;
import bbva.java2.minibanco_lab1.domain.model.Cuenta;

import java.util.List;
import java.util.Optional;

public interface IClienteRepository {
    Cliente guardarCliente(Cliente cliente);
    List<Cliente> listarClientes();
    Optional<Cliente> buscarClientePorId(Long idCliente);
    Optional<Cliente> buscaClientePorDni(String dni);
    boolean existeClientePorEmail(String email);
    boolean existeClientePorDni(String dni);
    Cliente actualizarCliente(Cliente cliente);

}






































































































































































































































































