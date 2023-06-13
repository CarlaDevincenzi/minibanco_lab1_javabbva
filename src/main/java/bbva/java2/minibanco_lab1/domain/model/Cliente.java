package bbva.java2.minibanco_lab1.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter @Setter
@NoArgsConstructor
public class Cliente {

    private Long idCliente;
    private String nombre;
    private String apellido;
    private String dni;
    private String email;
    private String domicilio;
    private String telefono;
    private List<Long> cuentasPropias = new ArrayList<>();
    private List<Long> cuentasCotituladas = new ArrayList<>();

    public Cliente(String nombre, String apellido, String dni, String email, String domicilio, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
        this.domicilio = domicilio;
        this.telefono = telefono;
    }
}
