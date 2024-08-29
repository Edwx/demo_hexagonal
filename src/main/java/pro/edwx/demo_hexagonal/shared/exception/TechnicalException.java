package pro.edwx.demo_hexagonal.shared.exception;

public class TechnicalException extends RuntimeException {

    public TechnicalException(String message) {
        super(message);
    }

    /**
     * Constructor que acepta un mensaje de error y una causa.
     * @param message
     * @param cause
     */
    public TechnicalException(String message, Throwable cause) {
        super(message, cause);
    }
}
