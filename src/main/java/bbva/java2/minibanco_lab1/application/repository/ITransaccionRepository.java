package bbva.java2.minibanco_lab1.application.repository;

import bbva.java2.minibanco_lab1.domain.model.Transaccion;

public interface ITransaccionRepository {

     Transaccion guardarTransaccion(Transaccion transaccion);

    //Optional<Transaccion> buscarTransaccionPorId(Long id);

}
