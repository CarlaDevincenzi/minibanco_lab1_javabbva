package bbva.java2.minibanco_lab1.infraestructure.repositoryImpl;

import bbva.java2.minibanco_lab1.application.repository.IClienteRepository;
import bbva.java2.minibanco_lab1.domain.model.Cliente;
import bbva.java2.minibanco_lab1.infraestructure.entities.ClienteEntity;
import bbva.java2.minibanco_lab1.infraestructure.mapper.ClienteEntityMapper;
import bbva.java2.minibanco_lab1.infraestructure.repositoryImpl.springdatajpa.IClienteSpringRepository;
import bbva.java2.minibanco_lab1.presentation.response.exception.NotFoundException;
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
    public List<Cliente> findAllClientes() {
        return clienteSpringRepository.findAll()
                .stream()
                .map(cte -> clienteMapper.entityToDomain(cte))
                .toList();
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
    public Optional<Cliente> buscaClientePorEmail(String email) {
        Optional<ClienteEntity> clienteEntity = clienteSpringRepository.findByEmail(email);
        Optional<Cliente> clienteOptional = Optional.empty();

        if(clienteEntity.isPresent()) {
            clienteOptional = Optional.ofNullable(clienteMapper.entityToDomain(clienteEntity.get()));
        }

        return clienteOptional;
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
    public boolean existeClientePorId(Long id) {
        return clienteSpringRepository.existsById(id);
    }

    @Override
    public void bajaCliente(String userEmail) {
        Optional<ClienteEntity> clienteEntity = clienteSpringRepository.findByEmail(userEmail);
        if(clienteEntity.isPresent()) {
            clienteEntity.get().setAlta(false);
        } else {
            throw new NotFoundException("Cliente no encontrado");
        }
    }

    @Override
    public Cliente actualizarCliente(ClienteEntity cliente) {
        clienteSpringRepository.save(cliente);
        return clienteMapper.entityToDomain(cliente);
    }

}
