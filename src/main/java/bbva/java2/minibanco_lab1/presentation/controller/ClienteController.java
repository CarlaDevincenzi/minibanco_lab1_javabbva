package bbva.java2.minibanco_lab1.presentation.controller;

import bbva.java2.minibanco_lab1.application.usecase.IClienteUseCase;
import bbva.java2.minibanco_lab1.presentation.mapper.ClientePresentacionMapper;
import bbva.java2.minibanco_lab1.presentation.request.clienteReq.ClienteCreateReq;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/minibanco/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final IClienteUseCase clienteUseCase;
    private final ClientePresentacionMapper clientePresentacionMapper;

    @PostMapping(value = "/crear", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> crearCliente(@Valid @RequestBody ClienteCreateReq clienteCreateReq) {

        return new ResponseEntity<>(clienteUseCase.guardarCliente(clienteCreateReq), HttpStatus.OK);
    }

    @GetMapping(value = "/listar/{email}", produces = "application/json")
    public ResponseEntity<?> listarUnCliente(@PathVariable String email) {

        return new ResponseEntity<>(clienteUseCase.listarUnClienteConCuentas(email), HttpStatus.OK);
    }

    @GetMapping(value = "/listar-activos", produces = "application/json")
    public ResponseEntity<?> listarClientesActivos() {

        return new ResponseEntity<>(clienteUseCase.listarClientesActivos(), HttpStatus.OK);
    }

    @GetMapping(value = "/listar-inactivos", produces = "application/json")
    public ResponseEntity<?> listarClientesInactivos() {

        return new ResponseEntity<>(clienteUseCase.listarClientesInactivos(), HttpStatus.OK);
    }
    @GetMapping(value = "/auth/ver-info", produces = "application/json")
    public ResponseEntity<?> listarClienteAutenticado(Authentication auth) {

        return new ResponseEntity<>(clienteUseCase.listarUnClienteConCuentas(auth.getName()), HttpStatus.OK);
    }



}
