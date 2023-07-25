package bbva.java2.minibanco_lab1.domain.model;

import bbva.java2.minibanco_lab1.domain.enums.MonedaEnum;
import lombok.*;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cuenta {

    private Long idCuenta;
    private String numeroCuenta;
    @Enumerated(EnumType.STRING)
    private MonedaEnum moneda;
    @PositiveOrZero
    private BigDecimal saldo;
    private Long titular;
    private Long cotitular;
    private List<Long> historialTransacciones = new ArrayList<>();
    private boolean alta;
}
