package bbva.java2.minibanco_lab1.presentation.mapper;

import bbva.java2.minibanco_lab1.domain.model.Cliente;
import bbva.java2.minibanco_lab1.presentation.request.clienteReq.ClienteCreateReq;
import bbva.java2.minibanco_lab1.presentation.response.clienteResp.ClienteResponse;
import org.springframework.stereotype.Component;

@Component
public class ClientePresentacionMapper {
    public Cliente requestToDomain(ClienteCreateReq clienteReq) {
        Cliente cliente = new Cliente();

        cliente.setNombre(clienteReq.getNombre());
        cliente.setApellido(clienteReq.getApellido());
        cliente.setDni(clienteReq.getDni());
        cliente.setEmail(clienteReq.getEmail());
        cliente.setDomicilio(clienteReq.getDomicilio());
        cliente.setTelefono(clienteReq.getTelefono());

        return cliente;
    }

    public ClienteResponse domainToResponse(Cliente cliente) {
        ClienteResponse clienteResponse = new ClienteResponse();

        clienteResponse.setIdCliente(cliente.getIdCliente());
        clienteResponse.setNombre(cliente.getNombre());
        clienteResponse.setApellido(cliente.getApellido());
        clienteResponse.setDni(cliente.getDni());
        clienteResponse.setEmail(cliente.getEmail());
        clienteResponse.setDomicilio(cliente.getDomicilio());
        clienteResponse.setTelefono(cliente.getTelefono() != null ? cliente.getTelefono() : "------");

        return clienteResponse;
    }
}
