package bbva.java2.minibanco_lab1.presentation.controller;

import bbva.java2.minibanco_lab1.application.repository.IClienteRepository;
import bbva.java2.minibanco_lab1.application.usecase.IClienteUseCase;
import bbva.java2.minibanco_lab1.domain.model.Cliente;
import bbva.java2.minibanco_lab1.presentation.mapper.ClientePresentacionMapper;
import bbva.java2.minibanco_lab1.presentation.request.clienteReq.ClienteCreateReq;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/minibanco")
@RequiredArgsConstructor
public class ClienteController {
    //private final IClienteRepository clienteRepository;
    private final IClienteUseCase clienteUseCase;
    private final ClientePresentacionMapper clientePresentacionMapper;

    @PostMapping(value = "/crear-cliente", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> crearCliente(@Valid @RequestBody ClienteCreateReq clienteCreateReq) {
        //Cliente cliente = clientePresentacionMapper.requestToDomain(clienteCreateReq);
        return new ResponseEntity<>(clienteUseCase.guardarCliente(clienteCreateReq), HttpStatus.OK);
    }

}
