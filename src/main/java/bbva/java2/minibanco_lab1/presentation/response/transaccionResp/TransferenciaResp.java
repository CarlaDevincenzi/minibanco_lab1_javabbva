package bbva.java2.minibanco_lab1.presentation.response.transaccionResp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransferenciaResp {
    private DebitoDepositoResp origenTr;
    private DebitoDepositoResp destinoTr;
}
