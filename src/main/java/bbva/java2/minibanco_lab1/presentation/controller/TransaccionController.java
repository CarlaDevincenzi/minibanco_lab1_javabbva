package bbva.java2.minibanco_lab1.presentation.controller;

import bbva.java2.minibanco_lab1.application.usecase.IClienteUseCase;
import bbva.java2.minibanco_lab1.application.usecase.ITransaccionUseCase;
import bbva.java2.minibanco_lab1.presentation.request.transaccionReq.DebitoDepositoCreateReq;
import bbva.java2.minibanco_lab1.presentation.request.transaccionReq.TransferenciaReq;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/minibanco/transaccion")
@RequiredArgsConstructor
public class TransaccionController {

    private final ITransaccionUseCase trService;
    private final IClienteUseCase clienteService;
    @PostMapping(value = "auth/deposito", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> depositar(@Valid @RequestBody DebitoDepositoCreateReq req, Authentication auth) {
        req.setUserEmail(auth.getName());
        return new ResponseEntity<>(trService.deposito_debito_transaccion(req), HttpStatus.OK);
    }

    @PostMapping(value = "auth/debito", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> debitar(@Valid @RequestBody DebitoDepositoCreateReq req, Authentication auth) {
        req.setUserEmail(auth.getName());
        if(!clienteService.esCuentaCliente(auth.getName(), req.getCuentaOrigen())) {
            throw new RuntimeException("No es propietario de la cuenta para esta operacion");
        }
        return new ResponseEntity<>(trService.deposito_debito_transaccion(req), HttpStatus.OK);
    }

    @PostMapping(value = "auth/transferencia", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> transferir(@Valid @RequestBody TransferenciaReq transferenciaReq, Authentication auth) {
        transferenciaReq.getOrigenTr().setUserEmail(auth.getName());
        transferenciaReq.getDestinoTr().setUserEmail(auth.getName());

        if(!clienteService.esCuentaCliente(auth.getName(), transferenciaReq.getOrigenTr().getCuentaOrigen())) {
            throw new RuntimeException("No es propietario de la cuenta para esta operacion");
        }
        return new ResponseEntity<>(trService.transferencia(transferenciaReq), HttpStatus.OK);
    }


}
