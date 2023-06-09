package bbva.java2.minibanco_lab1.presentation.request.cuentaReq;

import bbva.java2.minibanco_lab1.domain.enums.MonedaEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
@Getter
public class CuentaCreateReq {
   // @NotBlank(message = "Debe especificarse el tipo de moneda")
    @NotNull(message = "Debe especificarse el tipo de moneda")
    private MonedaEnum moneda;
    @NotNull(message = "Debe especificarse un cliente titular")
    private Long titular;
    private Long cotitular;
}
