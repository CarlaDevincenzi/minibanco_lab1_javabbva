package bbva.java2.minibanco_lab1.infraestructure.repositoryImpl;

import bbva.java2.minibanco_lab1.application.repository.ITransaccionRepository;
import bbva.java2.minibanco_lab1.domain.model.Transaccion;
import bbva.java2.minibanco_lab1.infraestructure.entities.TransaccionEntity;
import bbva.java2.minibanco_lab1.infraestructure.mapper.TransaccionEntityMapper;
import bbva.java2.minibanco_lab1.infraestructure.repositoryImpl.springdatajpa.ITransaccionSpringRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TransaccionRepositoryImpl implements ITransaccionRepository {

    private final ITransaccionSpringRepository trSpringRepository;
    private final TransaccionEntityMapper trMapper;
    @Override
    public Transaccion guardarTransaccion(Transaccion transaccion) {

        TransaccionEntity trEntity = trMapper.domainToEntity(transaccion);
        trSpringRepository.save(trEntity);

        return trMapper.EntityToDomain(trEntity);
    }
}
