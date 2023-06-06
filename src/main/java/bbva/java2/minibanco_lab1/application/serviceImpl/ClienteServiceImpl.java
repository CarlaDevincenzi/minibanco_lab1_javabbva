package bbva.java2.minibanco_lab1.application.serviceImpl;

import bbva.java2.minibanco_lab1.application.repository.IClienteRepository;
import bbva.java2.minibanco_lab1.application.usecase.IClienteUseCase;
import bbva.java2.minibanco_lab1.domain.model.Cliente;
import bbva.java2.minibanco_lab1.presentation.response.exception.UsuarioExistenteException;
import bbva.java2.minibanco_lab1.presentation.mapper.ClientePresentacionMapper;
import bbva.java2.minibanco_lab1.presentation.request.clienteReq.ClienteCreateReq;
import bbva.java2.minibanco_lab1.presentation.response.clienteResp.ClienteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements IClienteUseCase {

    private final ClientePresentacionMapper clientePresentacionMapper;
    private final IClienteRepository clienteRepository;
    @Override
    public ClienteResponse guardarCliente(ClienteCreateReq clienteCreateReq) {
        boolean existecliente = clienteRepository.existeClientePorDni(clienteCreateReq.getDni())
                || clienteRepository.existeClientePorEmail(clienteCreateReq.getEmail());

        if(existecliente) {
            throw new UsuarioExistenteException("El usuario ya se encuentra registrado");
        }
        Cliente cliente = clientePresentacionMapper.requestToDomain(clienteCreateReq);
        Cliente guardado = clienteRepository.guardarCliente(cliente);

        return clientePresentacionMapper.domainToResponse(guardado);
    }
}
