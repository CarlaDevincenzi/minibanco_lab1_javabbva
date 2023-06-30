package bbva.java2.minibanco_lab1.presentation.request.cuentaReq;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CuentaAddCotitularReq {
    @NotNull(message = "Debe especificarse la cuenta")
    private Long idCuenta;
    @NotNull(message = "Debe especificarse el cliente a agregar como cotitular")
    private Long idCotitular;
}
