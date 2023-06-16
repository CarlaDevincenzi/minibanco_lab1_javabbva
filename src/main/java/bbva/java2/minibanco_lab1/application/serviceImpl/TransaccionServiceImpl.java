package bbva.java2.minibanco_lab1.application.serviceImpl;

import bbva.java2.minibanco_lab1.application.repository.IClienteRepository;
import bbva.java2.minibanco_lab1.application.repository.ICuentaRepository;
import bbva.java2.minibanco_lab1.application.repository.ITransaccionRepository;
import bbva.java2.minibanco_lab1.application.usecase.ICuentaUseCase;
import bbva.java2.minibanco_lab1.application.usecase.ITransaccionUseCase;
import bbva.java2.minibanco_lab1.domain.enums.TipoTransaccionEnum;
import bbva.java2.minibanco_lab1.domain.model.Cuenta;
import bbva.java2.minibanco_lab1.domain.model.Transaccion;
import bbva.java2.minibanco_lab1.infraestructure.mapper.CuentaEntityMapper;
import bbva.java2.minibanco_lab1.infraestructure.repositoryImpl.springdatajpa.ICuentaSpringRepository;
import bbva.java2.minibanco_lab1.presentation.mapper.CuentaPresentacionMapper;
import bbva.java2.minibanco_lab1.presentation.mapper.TransaccionPresentacionMapper;
import bbva.java2.minibanco_lab1.presentation.request.transaccionReq.DebitoDepositoCreateReq;
import bbva.java2.minibanco_lab1.presentation.response.exception.DineroInsuficienteException;
import bbva.java2.minibanco_lab1.presentation.response.exception.NotFoundException;
import bbva.java2.minibanco_lab1.presentation.response.transaccionResp.DebitoDepositoResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransaccionServiceImpl implements ITransaccionUseCase {

    private final ITransaccionRepository trRepository;
    private final ICuentaUseCase cuentaService;
    private final TransaccionPresentacionMapper trPresentacionMapper;

    @Override
    public DebitoDepositoResp debito(DebitoDepositoCreateReq req) {
        return null;
    }

    @Override
    public DebitoDepositoResp deposito_debito_transaccion(DebitoDepositoCreateReq req) {
        Transaccion transaccion = trPresentacionMapper.requestToDomain(req);
        transaccion.setDiahoraTransaccion(LocalDateTime.now());
        try {
            cuentaService.updateMonto(req.getCuentaOrigen(), req.getMonto(), req.getTipoTransaccion());
            transaccion = trRepository.guardarTransaccion(transaccion);
        } catch(DineroInsuficienteException | NotFoundException e) {
            throw e;
        }
        return trPresentacionMapper.DomainToDebitoDepositoResponse(transaccion);
    }
}
