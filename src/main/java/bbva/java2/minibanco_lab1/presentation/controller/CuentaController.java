package bbva.java2.minibanco_lab1.presentation.controller;

import bbva.java2.minibanco_lab1.application.usecase.ICuentaUseCase;
import bbva.java2.minibanco_lab1.domain.enums.MonedaEnum;
import bbva.java2.minibanco_lab1.presentation.mapper.CuentaPresentacionMapper;
import bbva.java2.minibanco_lab1.presentation.request.cuentaReq.CuentaAddCotitularReq;
import bbva.java2.minibanco_lab1.presentation.request.cuentaReq.CuentaCreateReq;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/minibanco/cuentas")
@RequiredArgsConstructor
public class CuentaController {

    private final ICuentaUseCase cuentaUseCase;

    @PostMapping(value = "/crear", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> crearCuenta(@Valid @RequestBody CuentaCreateReq cuentaCreateReq) {

        return new ResponseEntity<>(cuentaUseCase.guardarCuenta(cuentaCreateReq), HttpStatus.OK);
    }

    @PostMapping(value = "/auth/crear", produces = "application/json")
    public ResponseEntity<?> crearCuentaAutenticado(@NotNull @RequestParam MonedaEnum tipoMoneda, Authentication auth) {
        CuentaCreateReq cuentaCreateReq = new CuentaCreateReq();
        cuentaCreateReq.setMoneda(tipoMoneda);
        cuentaCreateReq.setTitular(auth.getName());
        return new ResponseEntity<>(cuentaUseCase.guardarCuenta(cuentaCreateReq), HttpStatus.OK);
    }

    @PatchMapping(value = "/agregar-cotitular", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> agregarCotitularCuenta(@Valid @RequestBody CuentaAddCotitularReq addCotitularReq) {

        return new ResponseEntity<>(cuentaUseCase.agregarCotitularAcuenta(addCotitularReq), HttpStatus.OK);
    }

    @PatchMapping(value = "/auth/agregar-cotitular", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> agregarCotitularCuentaAutenticado(@Valid @RequestBody CuentaAddCotitularReq addCotitularReq,
                                                               Authentication auth) {
        // TODO ver cambio de implementacion para Cliente
        return new ResponseEntity<>(cuentaUseCase.agregarCotitularAcuenta(addCotitularReq), HttpStatus.OK);
    }

    @GetMapping(value = "/listar/{id}", produces = "application/json")
    public ResponseEntity<?> getCuentaConTransacciones(@PathVariable Long id) {

        return new ResponseEntity<>(cuentaUseCase.mostrarUnaCuentaConTransacciones(id), HttpStatus.OK);
    }

    @GetMapping(value = "/auth/listar/movimientos-cuenta", produces = "application/json")
    public ResponseEntity<?> getMovimientosCuenta(@RequestParam Long id, Authentication auth) {
        // TODO ver cambio de implementacion para Cliente
        return new ResponseEntity<>(cuentaUseCase.mostrarUnaCuentaConTransacciones(id), HttpStatus.OK);
    }

}
