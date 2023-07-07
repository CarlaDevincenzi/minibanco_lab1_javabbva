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

    @GetMapping(value = "/listar/{dni}", produces = "application/json")
    public ResponseEntity<?> listarUnCliente(@PathVariable String dni) {

        return new ResponseEntity<>(clienteUseCase.listarUnClienteConCuentas(dni), HttpStatus.OK);
    }

    @GetMapping(value = "/listar", produces = "application/json")
    public ResponseEntity<?> listarTodosLosCliente() {
        // TODO implementar metodo en service y repository
        return new ResponseEntity<>("Listar todos los clientes -> ROLE_ADMIN", HttpStatus.OK);
    }

    @GetMapping(value = "/auth/listar", produces = "application/json")
    public ResponseEntity<?> listarClienteAutenticado(Authentication auth) {
        // TODO implementar metodo en las capas
        return new ResponseEntity<>("listar al cliente autenticado -> ROLE_CLIENTE", HttpStatus.OK);
    }



}
