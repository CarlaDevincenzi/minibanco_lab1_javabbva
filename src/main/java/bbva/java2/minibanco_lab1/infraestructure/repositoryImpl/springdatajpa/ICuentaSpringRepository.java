package bbva.java2.minibanco_lab1.infraestructure.repositoryImpl.springdatajpa;

import bbva.java2.minibanco_lab1.infraestructure.entities.CuentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICuentaSpringRepository extends JpaRepository<CuentaEntity, Long> {
    Optional<CuentaEntity> findByNumeroCuenta(String numeroCuenta);
}
