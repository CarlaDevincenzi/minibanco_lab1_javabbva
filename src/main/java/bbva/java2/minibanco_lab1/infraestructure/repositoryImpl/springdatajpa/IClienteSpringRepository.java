package bbva.java2.minibanco_lab1.infraestructure.repositoryImpl.springdatajpa;

import bbva.java2.minibanco_lab1.infraestructure.entities.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IClienteSpringRepository extends JpaRepository<ClienteEntity, Long> {

    boolean existsByDni(String dni);
    boolean existsByEmail(String mail);
    Optional<ClienteEntity> findByDni(String dni);
    Optional<ClienteEntity> findByEmail(String email);
}
