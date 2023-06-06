package bbva.java2.minibanco_lab1.infraestructure.mapper;

import bbva.java2.minibanco_lab1.domain.model.Cliente;
import bbva.java2.minibanco_lab1.infraestructure.entities.ClienteEntity;
import org.springframework.stereotype.Component;

@Component
public class ClienteEntityMapper {

    public ClienteEntity domainToEntity(Cliente cliente) {
        ClienteEntity clienteEntity = new ClienteEntity();

        clienteEntity.setNombre(cliente.getNombre());
        clienteEntity.setApellido(cliente.getApellido());
        clienteEntity.setDni(cliente.getDni());
        clienteEntity.setEmail(cliente.getEmail());
        clienteEntity.setDomicilio(cliente.getDomicilio());
        clienteEntity.setTelefono(cliente.getTelefono());

        return clienteEntity;
    }
}
