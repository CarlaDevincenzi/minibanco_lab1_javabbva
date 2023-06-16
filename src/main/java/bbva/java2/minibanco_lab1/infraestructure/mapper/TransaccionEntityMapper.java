package bbva.java2.minibanco_lab1.infraestructure.mapper;

import bbva.java2.minibanco_lab1.application.usecase.ICuentaUseCase;
import bbva.java2.minibanco_lab1.domain.model.Cuenta;
import bbva.java2.minibanco_lab1.domain.model.Transaccion;
import bbva.java2.minibanco_lab1.infraestructure.entities.ClienteEntity;
import bbva.java2.minibanco_lab1.infraestructure.entities.CuentaEntity;
import bbva.java2.minibanco_lab1.infraestructure.entities.TransaccionEntity;
import bbva.java2.minibanco_lab1.infraestructure.repositoryImpl.springdatajpa.ICuentaSpringRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransaccionEntityMapper {

    private final ICuentaSpringRepository cuentaSpringRepository;

    public TransaccionEntity domainToEntity(Transaccion transaccion) {
        return new TransaccionEntity(transaccion.getTipoTransaccion(),
                transaccion.getIdCliente(),
                transaccion.getDiahoraTransaccion(),
                cuentaSpringRepository.findById(transaccion.getCuentaOrigen()).get(),
                transaccion.getDescripcion(),
                transaccion.getMonto());

    }

    public Transaccion EntityToDomain(TransaccionEntity trEntity) {
        return new Transaccion(trEntity.getIdTransaccion(),
                trEntity.getTipoTransaccion(),
                trEntity.getIdCliente(),
                trEntity.getDiahoraTransaccion(),
                trEntity.getCuentaOrigen().getIdCuenta(),
                trEntity.getDescripcion(),
                trEntity.getMonto());

    }
}
