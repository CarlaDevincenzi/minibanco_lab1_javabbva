package bbva.java2.minibanco_lab1.presentation.response.cuentaResp;

import bbva.java2.minibanco_lab1.domain.enums.MonedaEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class CuentaCreateResp {
    private Long idCuenta;
    private String numeroCuenta ;
    private MonedaEnum moneda;
    private BigDecimal saldo;
    private Long titular;
    private Long cotitular;
}
