package bbva.java2.minibanco_lab1.infraestructure.repositoryImpl;

import bbva.java2.minibanco_lab1.application.repository.IClienteRepository;
import bbva.java2.minibanco_lab1.domain.model.Cliente;
import bbva.java2.minibanco_lab1.domain.model.Cuenta;
import bbva.java2.minibanco_lab1.infraestructure.entities.ClienteEntity;
import bbva.java2.minibanco_lab1.infraestructure.mapper.ClienteEntityMapper;
import bbva.java2.minibanco_lab1.infraestructure.repositoryImpl.springdatajpa.IClienteSpringRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
@RequiredArgsConstructor
public class ClienteRepositoryImpl implements IClienteRepository {

    private final IClienteSpringRepository clienteSpringRepository;
    private final ClienteEntityMapper clienteMapper;

    @Override
    public Cliente guardarCliente(Cliente cliente) {
        ClienteEntity clienteEntity = clienteMapper.domainToEntity(cliente);


        clienteSpringRepository.save(clienteEntity);


        return cliente;
    }

    @Override
    public List<Cliente> listarClientes() {
        // TODO implementar
        return null;
    }

    @Override
    public Optional<Cliente> buscarClientePorId(Long idCliente) {
        // TODO implementar
        return Optional.empty();
    }

    @Override
    public boolean existeClientePorEmail(String email) {
        return clienteSpringRepository.existsByEmail(email);
    }

    @Override
    public boolean existeClientePorDni(String dni) {
        return clienteSpringRepository.existsByDni(dni);
    }

    @Override
    public Cliente actualizarCliente(Cliente cliente) {
        // TODO implementar
        return null;
    }

    @Override
    public Cliente agregarCuenta(Cliente cliente, Cuenta cuenta) {
        // TODO implementar
        return null;
    }
}
