package bbva.java2.minibanco_lab1.presentation.response.exception;

public class DineroInsuficienteException extends RuntimeException{
    public DineroInsuficienteException() {
        super();
    }

    public DineroInsuficienteException(String mensaje) {
        super(mensaje);
    }
}
