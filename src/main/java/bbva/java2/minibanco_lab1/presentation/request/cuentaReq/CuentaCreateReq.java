package bbva.java2.minibanco_lab1.presentation.request.cuentaReq;

import bbva.java2.minibanco_lab1.domain.enums.MonedaEnum;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class CuentaCreateReq {

    @NotNull(message = "Debe especificarse el tipo de moneda")
    private MonedaEnum moneda;
    @NotNull(message = "Debe especificarse un cliente titular")
    private Long titular;
    private Long cotitular;
}
