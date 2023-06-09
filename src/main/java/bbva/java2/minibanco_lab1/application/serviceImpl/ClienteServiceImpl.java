package bbva.java2.minibanco_lab1.application.serviceImpl;

import bbva.java2.minibanco_lab1.application.repository.IClienteRepository;
import bbva.java2.minibanco_lab1.application.repository.ICuentaRepository;
import bbva.java2.minibanco_lab1.application.usecase.IClienteUseCase;
import bbva.java2.minibanco_lab1.domain.model.Cliente;
import bbva.java2.minibanco_lab1.domain.model.Cuenta;
import bbva.java2.minibanco_lab1.presentation.response.exception.UsuarioExistenteException;
import bbva.java2.minibanco_lab1.presentation.mapper.ClientePresentacionMapper;
import bbva.java2.minibanco_lab1.presentation.request.clienteReq.ClienteCreateReq;
import bbva.java2.minibanco_lab1.presentation.response.clienteResp.ClienteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements IClienteUseCase {

    private final ClientePresentacionMapper clientePresentacionMapper;
    private final IClienteRepository clienteRepository;
    private final ICuentaRepository cuentaRepository;
    @Override
    public ClienteResponse guardarCliente(Cliente cliente) {
        boolean existecliente = clienteRepository.existeClientePorDni(cliente.getDni())
                || clienteRepository.existeClientePorEmail(cliente.getEmail());

        if(existecliente) {
            throw new UsuarioExistenteException("El usuario ya se encuentra registrado");
        }

        Cliente guardado = clienteRepository.guardarCliente(cliente);

        return clientePresentacionMapper.domainToResponse(guardado);
    }

    @Override
    public Optional<Cliente> listarUnCliente(Long id) {

        return clienteRepository.buscarClientePorId(id);
    }

    @Override
    public void agregarCuentaACliente(Long idCliente, Long idCuenta, boolean titular) {
        // verificar si existe el cliente y la cuenta
        Optional<Cliente> clienteOptional = clienteRepository.buscarClientePorId(idCliente);
        Optional<Cuenta> cuentaOptional = cuentaRepository.buscarCuentaPorId(idCuenta);

        // Asumiendo que existen:
        clienteRepository.agregarCuenta(clienteOptional.get(), cuentaOptional.get(), titular);
    }
}
