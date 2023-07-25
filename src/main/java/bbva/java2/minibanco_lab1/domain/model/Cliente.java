package bbva.java2.minibanco_lab1.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String contrasenia;
    private String domicilio;
    private String telefono;
    private List<Long> cuentasPropias = new ArrayList<>();
    private List<Long> cuentasCotituladas = new ArrayList<>();
    private boolean alta;

    public Cliente(String nombre, String apellido, String dni, String email, String contrasenia, String domicilio, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
        this.contrasenia = contrasenia;
        this.domicilio = domicilio;
        this.telefono = telefono;
    }

}
