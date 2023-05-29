package bbva.java2.minibanco_lab1.domain.model;

import bbva.java2.minibanco_lab1.domain.enums.MonedaEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cuenta {

    @NotNull
    @Builder.Default
    private UUID numeroCuenta = UUID.randomUUID();
    @Enumerated(EnumType.STRING)
    @NotNull
    private MonedaEnum moneda;
    @PositiveOrZero
    private BigDecimal saldo;
    private UUID titular;
    private List<UUID> cotitulares;
    private List<UUID> historialTransacciones;
}
