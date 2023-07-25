package bbva.java2.minibanco_lab1.application.serviceImpl;

import bbva.java2.minibanco_lab1.application.repository.IClienteRepository;
import bbva.java2.minibanco_lab1.application.usecase.IClienteUseCase;
import bbva.java2.minibanco_lab1.application.usecase.ICuentaUseCase;
import bbva.java2.minibanco_lab1.domain.model.Cliente;
import bbva.java2.minibanco_lab1.domain.model.Cuenta;
import bbva.java2.minibanco_lab1.infraestructure.entities.ClienteEntity;
import bbva.java2.minibanco_lab1.infraestructure.repositoryImpl.springdatajpa.IClienteSpringRepository;
import bbva.java2.minibanco_lab1.presentation.mapper.ClientePresentacionMapper;
import bbva.java2.minibanco_lab1.presentation.request.clienteReq.ClienteCreateReq;
import bbva.java2.minibanco_lab1.presentation.request.clienteReq.ClienteUpdateReq;
import bbva.java2.minibanco_lab1.presentation.response.clienteResp.ClienteCreateResp;
import bbva.java2.minibanco_lab1.presentation.response.clienteResp.ClienteCuentasResp;
import bbva.java2.minibanco_lab1.presentation.response.exception.NotFoundException;
import bbva.java2.minibanco_lab1.presentation.response.exception.UsuarioExistenteException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements IClienteUseCase {

    private final ClientePresentacionMapper clientePresentacionMapper;
    private final IClienteRepository clienteRepository;
    private final IClienteSpringRepository clienteSpringRepository;
    private final ICuentaUseCase cuentaService;
    private final PasswordEncoder passwordEncoder;
    @Override
    public ClienteCreateResp guardarCliente(ClienteCreateReq clienteReq) {
        boolean existecliente = clienteRepository.existeClientePorDni(clienteReq.getDni())
                || clienteRepository.existeClientePorEmail(clienteReq.getEmail());

        if(existecliente) {
            throw new UsuarioExistenteException("El usuario ya se encuentra registrado");
        }
        Cliente cliente = clientePresentacionMapper.requestToDomain(clienteReq);
        cliente.setContrasenia(passwordEncoder.encode(clienteReq.getContrasenia()));
        cliente.setAlta(true);
        cliente = clienteRepository.guardarCliente(cliente);

        return clientePresentacionMapper.domainToCreateResponse(cliente);
    }

    @Override
    public ClienteCuentasResp listarUnClienteConCuentas(String email) {
        Optional<Cliente> clienteOptional = clienteRepository.buscaClientePorEmail(email);
        ClienteCuentasResp response;

        if(clienteOptional.isPresent()) {
            List<Cuenta> propias = clienteOptional.get().getCuentasPropias().stream()
                    .map(ctaId -> cuentaService.buscarCuentaPorId(ctaId)).toList();
            List<Cuenta> cotituladas = clienteOptional.get().getCuentasCotituladas().stream()
                    .map(ctaId -> cuentaService.buscarCuentaPorId(ctaId)).toList();

            response = clientePresentacionMapper
                    .domainToClienteCuentasResponse(clienteOptional.get(), propias, cotituladas);
        } else {
            throw new NotFoundException("El dni ingresado no corresponde a un cliente registrado");
        }
        return response;
    }

    @Override
    public List<ClienteCuentasResp> listarClientesActivos() {
        List<ClienteCuentasResp> activos = new ArrayList<>();
        List<Cliente> clientes = clienteRepository.findAllClientes();

        for(Cliente cli : clientes) {
            if(cli.isAlta()) {
                List<Cuenta> propias = cli.getCuentasPropias().stream()
                        .map(ctaId -> cuentaService.buscarCuentaPorId(ctaId)).toList();
                List<Cuenta> cotituladas = cli.getCuentasCotituladas().stream()
                        .map(ctaId -> cuentaService.buscarCuentaPorId(ctaId)).toList();

                activos.add(clientePresentacionMapper.domainToClienteCuentasResponse(cli, propias, cotituladas));
            }
        }
        return activos;
    }

    @Override
    public List<ClienteCreateResp> listarClientesInactivos() {
        List<ClienteCreateResp> inactivos = new ArrayList<>();
        List<Cliente> clientes = clienteRepository.findAllClientes();

        for(Cliente cli : clientes) {
            if(!cli.isAlta()) {
                inactivos.add(clientePresentacionMapper.domainToCreateResponse(cli));
            }
        }
        return inactivos;
    }

    @Override
    public boolean esCuentaCliente(String userEmail, Long idCuenta) {
        return this.esTitular(userEmail, idCuenta) || this.esCotitular(userEmail, idCuenta);
    }

    @Override
    public boolean esTitular(String userEmail, Long idCuenta) {
        Cuenta cuenta = cuentaService.buscarCuentaPorId(idCuenta);
        // recibe el mail de un user autenticado, por lo tanto no es necesario comprobar si existe
        Cliente cliente = clienteRepository.buscaClientePorEmail(userEmail).get();

        return Objects.equals(cuenta.getTitular(), cliente.getIdCliente());
    }

    @Override
    public boolean esCotitular(String userEmail, Long idCuenta) {
        Cuenta cuenta = cuentaService.buscarCuentaPorId(idCuenta);
        // recibe el mail de un user autenticado, por lo tanto no es necesario comprobar si existe
        Cliente cliente = clienteRepository.buscaClientePorEmail(userEmail).get();

        return Objects.equals(cuenta.getCotitular(), cliente.getIdCliente());
    }

    @Override
    public String bajaCliente(String userEmail) {
        Optional<Cliente> cliente = clienteRepository.buscaClientePorEmail(userEmail);
        if(cliente.isPresent()) {
            // Se dan de baja las cuentas en las que es titular
            for(Long idCta : cliente.get().getCuentasPropias()) {
                cuentaService.bajaCuenta(idCta);
            }
        }

        clienteRepository.bajaCliente(userEmail);
        return "Cliente dado de baja con exito";
    }

    @Override
    public ClienteCreateResp updateCliente(ClienteUpdateReq updateReq) {
        Optional<ClienteEntity> clienteEntity = clienteSpringRepository.findByEmail(updateReq.getEmail());
        if(clienteEntity.isPresent()) {
            ClienteEntity aModificar = clienteEntity.get();
            if(updateReq.getNombre() != null) aModificar.setNombre(updateReq.getNombre());
            if(updateReq.getApellido() != null) aModificar.setApellido(updateReq.getApellido());
            if(updateReq.getDni() != null) aModificar.setDni(updateReq.getDni());
            if(updateReq.getDomicilio() != null) aModificar.setDomicilio(updateReq.getDomicilio());
            if(updateReq.getTelefono() != null) aModificar.setTelefono(updateReq.getTelefono());
            if(updateReq.getContrasenia() != null) aModificar.setContrasenia(updateReq.getContrasenia());
            if(updateReq.getNuevoEmail() != null) aModificar.setEmail(updateReq.getNuevoEmail());

            Cliente modificado = clienteRepository.actualizarCliente(aModificar);
            return clientePresentacionMapper.domainToCreateResponse(modificado);
        }
        throw new NotFoundException("Cliente no encontrado");
    }

}
