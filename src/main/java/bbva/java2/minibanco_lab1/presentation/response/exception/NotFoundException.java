package bbva.java2.minibanco_lab1.presentation.response.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super();
    }

    public NotFoundException(String mensaje) {
        super(mensaje);
    }
}
