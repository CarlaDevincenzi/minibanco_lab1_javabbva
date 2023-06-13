package bbva.java2.minibanco_lab1.presentation.response.clienteResp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteCreateResp {

    private Long idCliente;
    private String nombre;
    private String apellido;
    private String dni;
    private String email;
    private String domicilio;
    private String telefono;
}
