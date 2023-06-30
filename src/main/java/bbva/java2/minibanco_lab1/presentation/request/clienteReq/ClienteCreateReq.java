package bbva.java2.minibanco_lab1.presentation.request.clienteReq;


import lombok.Getter;

import javax.validation.constraints.*;

@Getter
public class ClienteCreateReq {
    @NotBlank(message = "El nombre no puede estar vacio")
    @Pattern(regexp = "^[A-Za-z]+$", message = "el nombre debe contener solo letras")
    @Size(min = 2, max = 30, message = "El nombre debe contener entre 2 y 30 caracteres")
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacio")
    @Pattern(regexp = "^[A-Za-z]+$", message = "el apellido debe contener solo letras")
    @Size(min = 2, max = 30, message = "El apellido debe contener entre 2 y 30 caracteres")
    private String apellido;

    @NotBlank
    @Size(min = 8, max = 8)
    private String dni;

    @NotBlank(message = "Debe proporcionar un correo electronico")
    @Email
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "El email debe ser valido")
    private String email;

    @NotBlank(message = "Debe ingresar una contraseña")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$"
            ,message = "La contrasenia debe contener entre 8 y 20 caracteres, al menos 1 numero, 1 mayuscula," +
            " 1 minuscula y 1 caracter especial")
    private String contrasenia;

    @NotBlank
    @Size(max = 50, message = "El domicilio no puede tener mas de 50 caracteres")
    private String domicilio;

    @Pattern(regexp = "^[0-9]+$", message = "El telefono debe contener solo numeros")
    private String telefono;
}
