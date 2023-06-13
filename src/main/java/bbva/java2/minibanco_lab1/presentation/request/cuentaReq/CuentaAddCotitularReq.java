package bbva.java2.minibanco_lab1.presentation.request.cuentaReq;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CuentaAddCotitularReq {
    @NotNull(message = "Debe especificarse la cuenta")
    private Long idCuenta;
    @NotNull(message = "Debe especificarse el cliente a agregar como cotitular")
    private Long idCotitular;
}
