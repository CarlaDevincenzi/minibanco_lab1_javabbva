package bbva.java2.minibanco_lab1.application.serviceImpl;

import bbva.java2.minibanco_lab1.application.repository.IClienteRepository;
import bbva.java2.minibanco_lab1.application.repository.ICuentaRepository;
import bbva.java2.minibanco_lab1.application.usecase.ICuentaUseCase;
import bbva.java2.minibanco_lab1.domain.model.Cliente;
import bbva.java2.minibanco_lab1.domain.model.Cuenta;
import bbva.java2.minibanco_lab1.presentation.mapper.CuentaPresentacionMapper;
import bbva.java2.minibanco_lab1.presentation.request.cuentaReq.CuentaAddCotitularReq;
import bbva.java2.minibanco_lab1.presentation.request.cuentaReq.CuentaCreateReq;
import bbva.java2.minibanco_lab1.presentation.response.cuentaResp.CuentaCreateResp;
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
        Optional<Cliente> titular = clienteRepository.buscarClientePorId(cuentaReq.getTitular());

        if(titular.isPresent()) {
            Cuenta cuenta = cuentaPresentacionMapper.requestToDomain(cuentaReq);
            cuenta.setNumeroCuenta(UUID.randomUUID().toString().substring(0, 15));
            cuenta.setSaldo(new BigDecimal("0.0"));
            cuenta.setTitular(cuentaReq.getTitular());

            Cuenta guardada = cuentaRepository.guardarCuenta(cuenta);
            return cuentaPresentacionMapper.domainToResponse(guardada);
        } else {
            throw new NotFoundException("El titular ingresado no se encuentra registrado");
        }
    }

    @Override
    public CuentaCreateResp agregarCotitularAcuenta(CuentaAddCotitularReq addCotitularReq) {
        Cuenta cuenta = buscarCuentaPorId(addCotitularReq.getIdCuenta());
        Optional<Cliente> cotitular = clienteRepository.buscarClientePorId(addCotitularReq.getIdCotitular());
        if(cotitular.isPresent()) {
            cuenta.setCotitular(addCotitularReq.getIdCotitular());
            cuenta = cuentaRepository.guardarCuenta(cuenta);
            return cuentaPresentacionMapper.domainToResponse(cuenta);
        } else {
               throw new NotFoundException("El cotitular ingresado no se encuentra registrado");
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

}
