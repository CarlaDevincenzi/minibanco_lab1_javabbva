package bbva.java2.minibanco_lab1.presentation.controller;

import bbva.java2.minibanco_lab1.application.usecase.ICuentaUseCase;
import bbva.java2.minibanco_lab1.presentation.mapper.CuentaPresentacionMapper;
import bbva.java2.minibanco_lab1.presentation.request.cuentaReq.CuentaAddCotitularReq;
import bbva.java2.minibanco_lab1.presentation.request.cuentaReq.CuentaCreateReq;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @PatchMapping(value = "/agregar-cotitular", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> agregarCotitularCuenta(@Valid @RequestBody CuentaAddCotitularReq addCotitularReq) {

        return new ResponseEntity<>(cuentaUseCase.agregarCotitularAcuenta(addCotitularReq), HttpStatus.OK);
    }


    @GetMapping(value = "/listar/{id}", produces = "application/json")
    public ResponseEntity<?> CuentaConTransacciones(@PathVariable Long id) {

        return new ResponseEntity<>(cuentaUseCase.mostrarUnaCuentaConTransacciones(id), HttpStatus.OK);
    }

}
