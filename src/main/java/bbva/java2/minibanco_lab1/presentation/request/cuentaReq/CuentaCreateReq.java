package bbva.java2.minibanco_lab1.presentation.request.cuentaReq;

import bbva.java2.minibanco_lab1.domain.enums.MonedaEnum;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter @Setter
public class CuentaCreateReq {

    @NotNull(message = "Debe especificarse el tipo de moneda")
    private MonedaEnum moneda;
    @NotNull(message = "Debe especificarse un cliente titular")
    private String titular; // es el email

}
