package bbva.java2.minibanco_lab1.presentation.controller;

import bbva.java2.minibanco_lab1.application.usecase.ITransaccionUseCase;
import bbva.java2.minibanco_lab1.presentation.request.transaccionReq.DebitoDepositoCreateReq;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/minibanco/transaccion")
@RequiredArgsConstructor
public class TransaccionController {

    private final ITransaccionUseCase trService;

    @PostMapping(value = "/deposito", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> depositar(@Valid @RequestBody DebitoDepositoCreateReq req) {

        return new ResponseEntity<>(trService.deposito_debito_transaccion(req), HttpStatus.OK);
    }

    @PostMapping(value = "/debito", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> debitar(@Valid @RequestBody DebitoDepositoCreateReq req) {

        return new ResponseEntity<>(trService.deposito_debito_transaccion(req), HttpStatus.OK);
    }


}
