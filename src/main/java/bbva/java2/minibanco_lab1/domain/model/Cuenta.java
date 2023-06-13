package bbva.java2.minibanco_lab1.domain.model;

import bbva.java2.minibanco_lab1.domain.enums.MonedaEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

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
}
