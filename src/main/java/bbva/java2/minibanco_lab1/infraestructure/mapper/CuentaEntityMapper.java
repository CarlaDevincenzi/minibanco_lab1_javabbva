package bbva.java2.minibanco_lab1.infraestructure.mapper;

import bbva.java2.minibanco_lab1.application.repository.IClienteRepository;
import bbva.java2.minibanco_lab1.domain.model.Cuenta;
import bbva.java2.minibanco_lab1.infraestructure.entities.ClienteEntity;
import bbva.java2.minibanco_lab1.infraestructure.entities.CuentaEntity;
import bbva.java2.minibanco_lab1.infraestructure.repositoryImpl.springdatajpa.IClienteSpringRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CuentaEntityMapper {

    private final IClienteSpringRepository clienteRepository;
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
        return cuentaEntity;
    }

    public Cuenta entityToDomain(CuentaEntity cuentaEntity) {
        Cuenta cuenta = new Cuenta();
        cuenta.setIdCuenta(cuentaEntity.getIdCuenta());
        cuenta.setNumeroCuenta(cuentaEntity.getNumeroCuenta());
        cuenta.setMoneda(cuentaEntity.getMoneda());
        cuenta.setSaldo(cuentaEntity.getSaldo());
        cuenta.setTitular(cuentaEntity.getTitular().getIdCliente());
        cuenta.setCotitular(cuentaEntity.getCotitular() != null ?
                cuentaEntity.getCotitular().getIdCliente() : null);

        return cuenta;
    }

}
