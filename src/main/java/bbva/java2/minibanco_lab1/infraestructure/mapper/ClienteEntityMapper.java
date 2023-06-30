package bbva.java2.minibanco_lab1.infraestructure.mapper;

import bbva.java2.minibanco_lab1.application.repository.ICuentaRepository;
import bbva.java2.minibanco_lab1.domain.model.Cliente;
import bbva.java2.minibanco_lab1.domain.model.Cuenta;
import bbva.java2.minibanco_lab1.infraestructure.entities.ClienteEntity;
import bbva.java2.minibanco_lab1.infraestructure.entities.CuentaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ClienteEntityMapper {

    private final CuentaEntityMapper cuentaMapper;
    private final ICuentaRepository cuentaRepository;
    public ClienteEntity domainToEntity(Cliente cliente) {
        ClienteEntity clienteEntity = new ClienteEntity();

        clienteEntity.setNombre(cliente.getNombre());
        clienteEntity.setApellido(cliente.getApellido());
        clienteEntity.setDni(cliente.getDni());
        clienteEntity.setEmail(cliente.getEmail());
        clienteEntity.setContrasenia(cliente.getContrasenia());
        clienteEntity.setDomicilio(cliente.getDomicilio());
        clienteEntity.setTelefono(cliente.getTelefono());

        List<CuentaEntity> cuentasProp = new ArrayList<>();
        for(Long idCuenta : cliente.getCuentasPropias()) {
            Optional<Cuenta> cuenta = cuentaRepository.buscarCuentaPorId(idCuenta);
            cuentasProp.add(cuentaMapper.domainToEntity(cuenta.get()));
        }
        clienteEntity.setCuentasPropias(cuentasProp);

        List<CuentaEntity> cuentasCoti = new ArrayList<>();
        for(Long idCuenta : cliente.getCuentasCotituladas()) {
            Optional<Cuenta> cuenta = cuentaRepository.buscarCuentaPorId(idCuenta);
            cuentasCoti.add(cuentaMapper.domainToEntity(cuenta.get()));
        }
        clienteEntity.setCuentasCotituladas(cuentasCoti);

        return clienteEntity;
    }

    public Cliente entityToDomain(ClienteEntity clienteEntity) {
        Cliente cliente = new Cliente();

        cliente.setIdCliente(clienteEntity.getIdCliente());
        cliente.setNombre(clienteEntity.getNombre());
        cliente.setApellido(clienteEntity.getApellido());
        cliente.setDni(clienteEntity.getDni());
        cliente.setEmail(clienteEntity.getEmail());
        cliente.setContrasenia(clienteEntity.getContrasenia());
        cliente.setDomicilio(clienteEntity.getDomicilio());
        cliente.setTelefono(clienteEntity.getTelefono());

        List<Long> cuentasProp = new ArrayList<>();
        for(CuentaEntity  entity : clienteEntity.getCuentasPropias()) {
            cuentasProp.add(entity.getIdCuenta());
        }
        cliente.setCuentasPropias(cuentasProp);

        List<Long> cuentasCoti = new ArrayList<>();
        for(CuentaEntity  entity : clienteEntity.getCuentasCotituladas()) {
            cuentasCoti.add(entity.getIdCuenta());
        }
        cliente.setCuentasCotituladas(cuentasCoti);

        return cliente;
    }
}
