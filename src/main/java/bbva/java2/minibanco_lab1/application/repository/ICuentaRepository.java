package bbva.java2.minibanco_lab1.application.repository;

import bbva.java2.minibanco_lab1.domain.model.Cuenta;

import java.util.Optional;

public interface ICuentaRepository {
    Cuenta guardarCuenta(Cuenta cuenta);

    Optional<Cuenta> buscarCuentaPorId(Long id);
}
