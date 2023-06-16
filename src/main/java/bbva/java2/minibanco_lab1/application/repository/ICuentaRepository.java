package bbva.java2.minibanco_lab1.application.repository;

import bbva.java2.minibanco_lab1.domain.enums.TipoTransaccionEnum;
import bbva.java2.minibanco_lab1.domain.model.Cuenta;

import java.math.BigDecimal;
import java.util.Optional;

public interface ICuentaRepository {
    Cuenta guardarCuenta(Cuenta cuenta);
    Cuenta updateCuentaCotitular(Long idCuenta, Long idCotitular);
    void updateMonto(Long idCuenta, BigDecimal monto);
    Optional<Cuenta> buscarCuentaPorId(Long id);

}
