package bbva.java2.minibanco_lab1.infraestructure.repositoryImpl;

import bbva.java2.minibanco_lab1.application.repository.IClienteRepository;
import bbva.java2.minibanco_lab1.domain.model.Cliente;
import bbva.java2.minibanco_lab1.domain.model.Cuenta;
import bbva.java2.minibanco_lab1.infraestructure.entities.ClienteEntity;
import bbva.java2.minibanco_lab1.infraestructure.entities.CuentaEntity;
import bbva.java2.minibanco_lab1.infraestructure.mapper.ClienteEntityMapper;
import bbva.java2.minibanco_lab1.infraestructure.mapper.CuentaEntityMapper;
import bbva.java2.minibanco_lab1.infraestructure.repositoryImpl.springdatajpa.IClienteSpringRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ClienteRepositoryImpl implements IClienteRepository {

    private final IClienteSpringRepository clienteSpringRepository;
    private final ClienteEntityMapper clienteMapper;

    @Override
    public Cliente guardarCliente(Cliente cliente) {
        ClienteEntity clienteEntity = clienteMapper.domainToEntity(cliente);

        clienteSpringRepository.save(clienteEntity);

        return clienteMapper.entityToDomain(clienteEntity);
    }

    @Override
    public List<Cliente> listarClientes() {
        // TODO implementar
        return null;
    }

    @Override
    public Optional<Cliente> buscarClientePorId(Long idCliente) {
        Optional<ClienteEntity> clienteEntity = clienteSpringRepository.findById(idCliente);
        Optional<Cliente> clienteOptional = Optional.empty();

        if(clienteEntity.isPresent()) {
            clienteOptional = Optional.ofNullable(clienteMapper.entityToDomain(clienteEntity.get()));
        }

        return clienteOptional;
    }

    @Override
    public Optional<Cliente> buscaClientePorDni(String dni) {
        Optional<ClienteEntity> clienteEntity = clienteSpringRepository.findByDni(dni);
        Optional<Cliente> clienteOptional = Optional.empty();

        if(clienteEntity.isPresent()) {
            clienteOptional = Optional.ofNullable(clienteMapper.entityToDomain(clienteEntity.get()));
        }

        return clienteOptional;
    }

    @Override
    public Cliente buscaClientePorEmail(String email) {
        Optional<ClienteEntity> clienteEntity = clienteSpringRepository.findByEmail(email);
        Cliente cliente = null;

        if(clienteEntity.isPresent()) {
            cliente = clienteMapper.entityToDomain(clienteEntity.get());
        }

        return cliente;
    }

//    @Override
//    public Optional<Cliente> buscaClientePorEmail(String email) {
//        Optional<ClienteEntity> clienteEntity = clienteSpringRepository.findByEmail(email);
//        Optional<Cliente> clienteOptional = Optional.empty();
//
//        if(clienteEntity.isPresent()) {
//            clienteOptional = Optional.ofNullable(clienteMapper.entityToDomain(clienteEntity.get()));
//        }
//
//        return clienteOptional;
//    }

    @Override
    public boolean existeClientePorEmail(String email) {
        return clienteSpringRepository.existsByEmail(email);
    }

    @Override
    public boolean existeClientePorDni(String dni) {
        return clienteSpringRepository.existsByDni(dni);
    }

    @Override
    public boolean existeClientePorId(Long id) {
        return clienteSpringRepository.existsById(id);
    }

    @Override
    public Cliente actualizarCliente(Cliente cliente) {
        // TODO implementar
        return null;
    }

}
