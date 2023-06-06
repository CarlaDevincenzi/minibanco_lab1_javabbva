package bbva.java2.minibanco_lab1.presentation.response.clienteResp;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ClienteResponse {

    private Long idCliente;
    private String nombre;
    private String apellido;
    private String dni;
    private String email;
    private String domicilio;
    private String telefono;
}
