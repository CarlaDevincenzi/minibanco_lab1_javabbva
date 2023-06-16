package bbva.java2.minibanco_lab1.infraestructure.mapper;

import bbva.java2.minibanco_lab1.domain.model.Cuenta;
import bbva.java2.minibanco_lab1.infraestructure.entities.ClienteEntity;
import bbva.java2.minibanco_lab1.infraestructure.entities.CuentaEntity;
import bbva.java2.minibanco_lab1.infraestructure.repositoryImpl.springdatajpa.IClienteSpringRepository;
import bbva.java2.minibanco_lab1.infraestructure.repositoryImpl.springdatajpa.ITransaccionSpringRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CuentaEntityMapper {

    private final IClienteSpringRepository clienteRepository;
    private final ITransaccionSpringRepository trSpringRepository;
    public CuentaEntity domainToEntity(Cuenta cuenta) {
        CuentaEntity cuentaEntity = new CuentaEntity();

        cuentaEntity.setNumeroCuenta(cuenta.getNumeroCuenta());
        cuentaEntity.setMoneda(cuenta.getMoneda());
        cuentaEntity.setSaldo(cuenta.getSaldo());
        // se verifica previamente en el service que existan el cliente titular y cotitular
        ClienteEntity titular = clienteRepository.findById(cuenta.getTitular()).get();
        cuentaEntity.setTitular(titular);

        if(cuenta.getCotitular() != null) {
            ClienteEntity cotitular = clienteRepository.findById(cuenta.getCotitular()).get();
            cuentaEntity.setCotitular(cotitular);
        }

        cuentaEntity.setHistorialTransacciones(
                cuenta.getHistorialTransacciones().stream()
                        .map(tr -> trSpringRepository.findById(tr).get())
                        .toList());

        return cuentaEntity;
    }

    public Cuenta entityToDomain(CuentaEntity cuentaEntity) {
        return new Cuenta(cuentaEntity.getIdCuenta(),
                cuentaEntity.getNumeroCuenta(),
                cuentaEntity.getMoneda(),
                cuentaEntity.getSaldo(),
                cuentaEntity.getTitular().getIdCliente(),
                cuentaEntity.getCotitular() != null ?
                        cuentaEntity.getCotitular().getIdCliente() : null,
                cuentaEntity.getHistorialTransacciones().stream()
                        .map(tr -> tr.getIdTransaccion())
                        .toList());

    }

}
