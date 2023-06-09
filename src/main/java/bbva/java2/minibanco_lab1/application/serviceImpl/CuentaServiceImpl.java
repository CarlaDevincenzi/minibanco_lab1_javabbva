package bbva.java2.minibanco_lab1.application.serviceImpl;

import bbva.java2.minibanco_lab1.application.repository.IClienteRepository;
import bbva.java2.minibanco_lab1.application.repository.ICuentaRepository;
import bbva.java2.minibanco_lab1.application.usecase.IClienteUseCase;
import bbva.java2.minibanco_lab1.application.usecase.ICuentaUseCase;
import bbva.java2.minibanco_lab1.domain.model.Cliente;
import bbva.java2.minibanco_lab1.domain.model.Cuenta;
import bbva.java2.minibanco_lab1.presentation.mapper.CuentaPresentacionMapper;
import bbva.java2.minibanco_lab1.presentation.request.cuentaReq.CuentaCreateReq;
import bbva.java2.minibanco_lab1.presentation.response.cuentaResp.CuentaResponse;
import bbva.java2.minibanco_lab1.presentation.response.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CuentaServiceImpl implements ICuentaUseCase {

    private final ICuentaRepository cuentaRepository;
    private final IClienteRepository clienteRepository;
    private final IClienteUseCase clienteUseCase;
    private final CuentaPresentacionMapper cuentaPresentacionMapper;
    @Override
    public CuentaResponse guardarCuenta(CuentaCreateReq cuentaReq) {

        Optional<Cliente> titular = clienteRepository.buscarClientePorId(cuentaReq.getTitular());
       // Optional<Cliente> cotitular = null;

        if(titular.isPresent()) {
            Cuenta cuenta = cuentaPresentacionMapper.requestToDomain(cuentaReq);
            cuenta.setNumeroCuenta(UUID.randomUUID().toString().substring(0, 15));
            cuenta.setSaldo(new BigDecimal("0.0"));
            cuenta.setTitular(cuentaReq.getTitular());

            Cuenta guardada = cuentaRepository.guardarCuenta(cuenta);
           // titular.get().getCuentasPropias().add(guardada.getIdCuenta());
           // clienteUseCase.agregarCuentaACliente(titular.get().getIdCliente(), guardada.getIdCuenta(), true);

// pasar a otro metodo agregarCotitular
//            if(cuentaReq.getCotitular() != null) {
//                cotitular = clienteRepository.buscarClientePorId(cuentaReq.getTitular());
//                if(cotitular.isPresent()) {
//                    guardada.setCotitular(cotitular.get().getIdCliente());
//                    guardada = cuentaRepository.guardarCuenta(guardada);
//                    cotitular.get().getCuentas().add(guardada.getIdCuenta());
//                    clienteRepository.guardarCliente(cotitular.get());
//                } else {
//                    // lanzar excepcion
//                }
//            }
            return cuentaPresentacionMapper.domainToResponse(guardada);

        } else {
            throw new NotFoundException("El titular ingresado no se encuentra registrado");
        }
    }

}
