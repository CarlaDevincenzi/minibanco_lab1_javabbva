package bbva.java2.minibanco_lab1.application.serviceImpl;

import bbva.java2.minibanco_lab1.application.repository.IClienteRepository;
import bbva.java2.minibanco_lab1.application.repository.ICuentaRepository;
import bbva.java2.minibanco_lab1.application.usecase.ICuentaUseCase;
import bbva.java2.minibanco_lab1.domain.enums.TipoTransaccionEnum;
import bbva.java2.minibanco_lab1.domain.model.Cliente;
import bbva.java2.minibanco_lab1.domain.model.Cuenta;
import bbva.java2.minibanco_lab1.presentation.mapper.CuentaPresentacionMapper;
import bbva.java2.minibanco_lab1.presentation.request.cuentaReq.CuentaAddCotitularReq;
import bbva.java2.minibanco_lab1.presentation.request.cuentaReq.CuentaCreateReq;
import bbva.java2.minibanco_lab1.presentation.response.cuentaResp.CuentaCreateResp;
import bbva.java2.minibanco_lab1.presentation.response.cuentaResp.CuentaTransaccionesResp;
import bbva.java2.minibanco_lab1.presentation.response.exception.DineroInsuficienteException;
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
    private final CuentaPresentacionMapper cuentaPresentacionMapper;

    @Override
    public CuentaCreateResp guardarCuenta(CuentaCreateReq cuentaReq) {
        Optional<Cliente> titular = clienteRepository.buscaClientePorEmail(cuentaReq.getTitular());

        if(titular.isPresent()) {
            Cuenta cuenta = cuentaPresentacionMapper.requestToDomain(cuentaReq);
            cuenta.setNumeroCuenta(UUID.randomUUID().toString().substring(0, 15));
            cuenta.setSaldo(new BigDecimal("0.0"));
            cuenta.setTitular(titular.get().getIdCliente());
            cuenta.setAlta(true);

            Cuenta guardada = cuentaRepository.guardarCuenta(cuenta);
            return cuentaPresentacionMapper.domainToResponse(guardada);
        } else {
            throw new NotFoundException("El titular ingresado no se encuentra registrado");
        }
    }

    @Override
    public CuentaCreateResp agregarCotitularAcuenta(CuentaAddCotitularReq addCotitularReq) {
        if(clienteRepository.existeClientePorId(addCotitularReq.getIdCotitular())) {
            Cuenta cuenta = cuentaRepository.updateCuentaCotitular(addCotitularReq.getIdCuenta(),
                    addCotitularReq.getIdCotitular());
            return cuentaPresentacionMapper.domainToResponse(cuenta);
        } else {
            throw new NotFoundException("El cotitular ingresado no se encuentra registrado");
        }
    }
    @Override
    public void updateMonto(Long idCuenta, BigDecimal monto, TipoTransaccionEnum tipoTr) {
        Optional<Cuenta> cuentaOp = cuentaRepository.buscarCuentaPorId(idCuenta);

        if(cuentaOp.isPresent()) {
            BigDecimal montoActual = new BigDecimal("0.0");
            Cuenta cuenta = cuentaOp.get();
            if(tipoTr.equals(TipoTransaccionEnum.DEBITO) || tipoTr.equals(TipoTransaccionEnum.TRANSFERENCIA_DEBITO)) {
                if((cuenta.getSaldo().subtract(monto)).compareTo(BigDecimal.ZERO) < 0) {
                    throw new DineroInsuficienteException("La cuenta no posee fondos para realizar la operacion");
                } else {
                    montoActual = cuenta.getSaldo().subtract(monto);
                }
            } else if(tipoTr.equals(TipoTransaccionEnum.DEPOSITO) || tipoTr.equals(TipoTransaccionEnum.TRANSFERENCIA_CREDITO)) {
                montoActual = cuenta.getSaldo().add(monto);
            }
            cuentaRepository.updateMonto(idCuenta, montoActual);
        } else {
            throw new NotFoundException("La cuenta no se encuentra registrada");
        }
    }

    @Override
    public Cuenta buscarCuentaPorId(Long id) {
        Optional<Cuenta> cuentaOptional = cuentaRepository.buscarCuentaPorId(id);
        if(cuentaOptional.isEmpty()) {
            throw new NotFoundException("El id ingresado no pertenece a una cuenta registrada");
        }
        return cuentaOptional.get();
    }

    @Override
    public CuentaTransaccionesResp mostrarUnaCuentaConTransacciones(Long id) {
        Cuenta cuenta = buscarCuentaPorId(id);

        return cuentaPresentacionMapper.domainToCuentaTransaccionesResponse(cuenta);
    }

    public Cuenta buscarCuentaPorNumero(String numeroCta) {
        Optional<Cuenta> cuentaOptional = cuentaRepository.buscarCuentaPorNumeroCuenta(numeroCta);
        if(cuentaOptional.isEmpty()) {
            throw new NotFoundException("El numero de cuenta ingresado no pertenece a una cuenta registrada");
        }
        return cuentaOptional.get();
    }
    @Override
    public boolean igualTipoMoneda(Long idOrigen, Long idDestino) {
        Optional<Cuenta> origen = cuentaRepository.buscarCuentaPorId(idOrigen);
        Optional<Cuenta> destino = cuentaRepository.buscarCuentaPorId(idDestino);

        if(origen.isPresent() && destino.isPresent()) {
            return origen.get().getMoneda().equals(destino.get().getMoneda());
        }
        return false;
    }

}
