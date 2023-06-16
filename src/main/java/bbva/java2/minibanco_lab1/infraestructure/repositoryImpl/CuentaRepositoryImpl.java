package bbva.java2.minibanco_lab1.infraestructure.repositoryImpl;

import bbva.java2.minibanco_lab1.application.repository.IClienteRepository;
import bbva.java2.minibanco_lab1.application.repository.ICuentaRepository;
import bbva.java2.minibanco_lab1.domain.enums.TipoTransaccionEnum;
import bbva.java2.minibanco_lab1.domain.model.Cliente;
import bbva.java2.minibanco_lab1.domain.model.Cuenta;
import bbva.java2.minibanco_lab1.infraestructure.entities.ClienteEntity;
import bbva.java2.minibanco_lab1.infraestructure.entities.CuentaEntity;
import bbva.java2.minibanco_lab1.infraestructure.mapper.CuentaEntityMapper;
import bbva.java2.minibanco_lab1.infraestructure.repositoryImpl.springdatajpa.IClienteSpringRepository;
import bbva.java2.minibanco_lab1.infraestructure.repositoryImpl.springdatajpa.ICuentaSpringRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CuentaRepositoryImpl implements ICuentaRepository {

    private final ICuentaSpringRepository cuentaSpringRepository;
    private final CuentaEntityMapper cuentaEntityMapper;
    private final IClienteSpringRepository clienteSpringRepository;
    @Override
    public Cuenta guardarCuenta(Cuenta cuenta) {
        CuentaEntity cuentaEntity = cuentaEntityMapper.domainToEntity(cuenta);
        cuentaSpringRepository.save(cuentaEntity);

        return cuentaEntityMapper.entityToDomain(cuentaEntity);
    }

    @Override
    public Cuenta updateCuentaCotitular(Long idCuenta, Long idCotitular) {
        CuentaEntity cuentaEntity = cuentaSpringRepository.findById(idCuenta).get();
        ClienteEntity clienteEntity = clienteSpringRepository.findById(idCotitular).get();

        cuentaEntity.setCotitular(clienteEntity);
        cuentaSpringRepository.save(cuentaEntity);

        return cuentaEntityMapper.entityToDomain(cuentaEntity);
    }

    @Override
    public void updateMonto(Long idCuenta, BigDecimal monto) {
        CuentaEntity entity = cuentaSpringRepository.findById(idCuenta).get();
        entity.setSaldo(monto);

        cuentaSpringRepository.save(entity);
    }

    @Override
    public Optional<Cuenta> buscarCuentaPorId(Long id) {
        Optional<CuentaEntity> cuentaEntity = cuentaSpringRepository.findById(id);
        Optional<Cuenta> cuentaOptional = Optional.empty();

        if(cuentaEntity.isPresent()) {
            cuentaOptional = Optional.ofNullable(cuentaEntityMapper.entityToDomain(cuentaEntity.get()));
        }

        return cuentaOptional;
    }
}
