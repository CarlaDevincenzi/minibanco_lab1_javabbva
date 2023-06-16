package bbva.java2.minibanco_lab1.presentation.response.cuentaResp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CuentaTransaccionesResp {
    private CuentaSimpleResp cuentaSimpleResp;
    private List<Long> transacciones;
}
