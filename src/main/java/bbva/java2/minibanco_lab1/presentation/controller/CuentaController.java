package bbva.java2.minibanco_lab1.presentation.controller;

import bbva.java2.minibanco_lab1.application.usecase.ICuentaUseCase;
import bbva.java2.minibanco_lab1.presentation.mapper.CuentaPresentacionMapper;
import bbva.java2.minibanco_lab1.presentation.request.clienteReq.ClienteCreateReq;
import bbva.java2.minibanco_lab1.presentation.request.cuentaReq.CuentaAddCotitularReq;
import bbva.java2.minibanco_lab1.presentation.request.cuentaReq.CuentaCreateReq;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/minibanco/cuenta")
@RequiredArgsConstructor
public class CuentaController {

    private final ICuentaUseCase cuentaUseCase;
    private final CuentaPresentacionMapper cuentaPresentacionMapper;

    @PostMapping(value = "/crear", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> crearCuenta(@Valid @RequestBody CuentaCreateReq cuentaCreateReq) {

        return new ResponseEntity<>(cuentaUseCase.guardarCuenta(cuentaCreateReq), HttpStatus.OK);
    }

    @PostMapping(value = "/agregar-cotitular", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> agregarCotitularCuenta(@Valid @RequestBody CuentaAddCotitularReq addCotitularReq) {

        return new ResponseEntity<>(cuentaUseCase.agregarCotitularAcuenta(addCotitularReq), HttpStatus.OK);
    }
}
