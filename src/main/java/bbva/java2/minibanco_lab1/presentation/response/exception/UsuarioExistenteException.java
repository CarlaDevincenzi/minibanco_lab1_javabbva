package bbva.java2.minibanco_lab1.presentation.response.exception;

public class UsuarioExistenteException extends RuntimeException{

    public UsuarioExistenteException() {
        super();
    }
    public UsuarioExistenteException(String mensaje) {
        super(mensaje);
    }
}
