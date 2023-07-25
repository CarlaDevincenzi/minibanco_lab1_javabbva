package bbva.java2.minibanco_lab1.presentation.request.clienteReq;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
public class ClienteUpdateReq {

    @Pattern(regexp = "^[A-Za-z]+$", message = "el nombre debe contener solo letras")
    @Size(min = 2, max = 30, message = "El nombre debe contener entre 2 y 30 caracteres")
    private String nombre;

    @Pattern(regexp = "^[A-Za-z]+$", message = "el apellido debe contener solo letras")
    @Size(min = 2, max = 30, message = "El apellido debe contener entre 2 y 30 caracteres")
    private String apellido;

    @Size(min = 8, max = 8)
    private String dni;

    @Email
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "El email debe ser valido")
    private String email;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$"
            ,message = "La contrasenia debe contener entre 8 y 20 caracteres, al menos 1 numero, 1 mayuscula," +
            " 1 minuscula y 1 caracter especial")
    private String contrasenia;

    @Size(max = 50, message = "El domicilio no puede tener mas de 50 caracteres")
    private String domicilio;

    @Pattern(regexp = "^[0-9]+$", message = "El telefono debe contener solo numeros")
    private String telefono;
}
