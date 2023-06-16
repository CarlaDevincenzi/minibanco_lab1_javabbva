package bbva.java2.minibanco_lab1.presentation.mapper;


import bbva.java2.minibanco_lab1.domain.model.Transaccion;
import bbva.java2.minibanco_lab1.presentation.request.transaccionReq.DebitoDepositoCreateReq;
import bbva.java2.minibanco_lab1.presentation.response.transaccionResp.DebitoDepositoResp;
import org.springframework.stereotype.Component;

@Component
public class TransaccionPresentacionMapper {
    public Transaccion requestToDomain(DebitoDepositoCreateReq req) {
        return new Transaccion(req.getTipoTransaccion(),
                req.getIdCliente(),
                req.getCuentaOrigen(),
                req.getDescripcion(),
                req.getMonto());
    }

    public DebitoDepositoResp DomainToDebitoDepositoResponse(Transaccion resp) {
        return new DebitoDepositoResp(resp.getIdTransaccion(),
                resp.getTipoTransaccion(),
                resp.getIdCliente(),
                resp.getDiahoraTransaccion(),
                resp.getCuentaOrigen(),
                resp.getDescripcion(),
                resp.getMonto());
    }
}
