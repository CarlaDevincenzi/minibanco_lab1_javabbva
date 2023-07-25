package bbva.java2.minibanco_lab1.presentation.mapper;


import bbva.java2.minibanco_lab1.application.repository.IClienteRepository;
import bbva.java2.minibanco_lab1.domain.model.Cliente;
import bbva.java2.minibanco_lab1.domain.model.Transaccion;
import bbva.java2.minibanco_lab1.presentation.request.transaccionReq.DebitoDepositoCreateReq;
import bbva.java2.minibanco_lab1.presentation.response.transaccionResp.DebitoDepositoResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransaccionPresentacionMapper {

    private final IClienteRepository clienteRepository;
    public Transaccion requestToDomain(DebitoDepositoCreateReq req) {
        // es un usuario autenticado, no es necesario verificar el optional
        Cliente cliente = clienteRepository.buscaClientePorEmail(req.getUserEmail()).get();
        return new Transaccion(req.getTipoTransaccion(),
                cliente.getIdCliente(),
                req.getCuentaOrigen(),
                req.getDescripcion(),
                req.getMonto());
    }

    public DebitoDepositoResp domainToDebitoDepositoResponse(Transaccion resp) {
        // es un usuario autenticado, no es necesario verificar el optional
        Cliente cliente = clienteRepository.buscarClientePorId(resp.getIdCliente()).get();
        return new DebitoDepositoResp(resp.getIdTransaccion(),
                resp.getTipoTransaccion(),
                cliente.getEmail(),
                resp.getDiahoraTransaccion(),
                resp.getCuentaOrigen(),
                resp.getDescripcion(),
                resp.getMonto());
    }
}
