package bbva.java2.minibanco_lab1.infraestructure.repositoryImpl.springdatajpa;

import bbva.java2.minibanco_lab1.infraestructure.entities.TransaccionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITransaccionSpringRepository extends JpaRepository<TransaccionEntity, Long> {
}
