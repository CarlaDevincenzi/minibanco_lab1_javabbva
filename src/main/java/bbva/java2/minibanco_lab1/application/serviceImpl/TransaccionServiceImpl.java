package bbva.java2.minibanco_lab1.application.serviceImpl;

import bbva.java2.minibanco_lab1.application.repository.ITransaccionRepository;
import bbva.java2.minibanco_lab1.application.usecase.ICuentaUseCase;
import bbva.java2.minibanco_lab1.application.usecase.ITransaccionUseCase;
import bbva.java2.minibanco_lab1.domain.enums.TipoTransaccionEnum;
import bbva.java2.minibanco_lab1.domain.model.Transaccion;
import bbva.java2.minibanco_lab1.presentation.mapper.TransaccionPresentacionMapper;
import bbva.java2.minibanco_lab1.presentation.request.transaccionReq.DebitoDepositoCreateReq;
import bbva.java2.minibanco_lab1.presentation.request.transaccionReq.TransferenciaReq;
import bbva.java2.minibanco_lab1.presentation.response.exception.DineroInsuficienteException;
import bbva.java2.minibanco_lab1.presentation.response.exception.NotFoundException;
import bbva.java2.minibanco_lab1.presentation.response.transaccionResp.DebitoDepositoResp;
import bbva.java2.minibanco_lab1.presentation.response.transaccionResp.TransferenciaResp;
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
    public DebitoDepositoResp deposito_debito_transaccion(DebitoDepositoCreateReq req) {
        Transaccion transaccion = trPresentacionMapper.requestToDomain(req);
        transaccion.setDiahoraTransaccion(LocalDateTime.now());
        try {
            cuentaService.updateMonto(req.getCuentaOrigen(), req.getMonto(), req.getTipoTransaccion());
            transaccion = trRepository.guardarTransaccion(transaccion);
        } catch(DineroInsuficienteException | NotFoundException e) {
            throw e;
        }
        return trPresentacionMapper.domainToDebitoDepositoResponse(transaccion);
    }

    @Override
    public TransferenciaResp transferencia(TransferenciaReq req) {
        DebitoDepositoCreateReq origenTr = req.getOrigenTr();
        DebitoDepositoCreateReq destinoTr = req.getDestinoTr();

        if(!cuentaService.igualTipoMoneda(origenTr.getCuentaOrigen(), destinoTr.getCuentaOrigen())) {
            throw new RuntimeException("Las cuentas deben ser del mismo tipo de moneda");
        }

        origenTr.setDescripcion(origenTr.getDescripcion().concat(destinoTr.getCuentaOrigen().toString()));
        destinoTr.setDescripcion(destinoTr.getDescripcion().concat(origenTr.getCuentaOrigen().toString()));

        TransferenciaResp trResponse = new TransferenciaResp();
        try {
            DebitoDepositoResp debitoResp = deposito_debito_transaccion(origenTr);
            trResponse.setOrigenTr(debitoResp);
            try {
                DebitoDepositoResp depositoResp = deposito_debito_transaccion(destinoTr);
                trResponse.setDestinoTr(depositoResp);
            } catch(Exception ex) {
                // Hubo un problema con la acreditacion, se vuelve atras el debito realizado
                origenTr.setTipoTransaccion(TipoTransaccionEnum.DEPOSITO);
                origenTr.setDescripcion("Acreditacion monto por transferencia fallida");
                deposito_debito_transaccion(origenTr);
                throw ex;
            }

        } catch(DineroInsuficienteException | NotFoundException e) {
            throw e;
        }

        return trResponse;
    }
}
