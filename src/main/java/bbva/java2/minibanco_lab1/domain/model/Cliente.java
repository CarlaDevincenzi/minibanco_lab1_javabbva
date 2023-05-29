package bbva.java2.minibanco_lab1.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cliente {

    @Builder.Default
    private UUID idCliente = UUID.randomUUID();
    private String nombre;
    private String apellido;
    private String dni;
    private String email;
    private String domicilio; // crear clase Domicilio ?
    private String telefono; // crear clase Telefono (caracteristica, prefijo, sufijo) ?
    private List<UUID> cuentasPropias;
    private List<UUID> cuentasCotituladas;
}
