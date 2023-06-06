package bbva.java2.minibanco_lab1.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    private Long idCliente;
    private String nombre;
    private String apellido;
    private String dni;
    private String email;
    private String domicilio;
    private String telefono;
    private List<UUID> cuentas;
}
