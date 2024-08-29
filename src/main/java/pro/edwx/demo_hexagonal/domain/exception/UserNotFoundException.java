package pro.edwx.demo_hexagonal.domain.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructor que acepta un mensaje de error y una causa.
     * @param message
     * @param cause
     */
    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor que acepta un id de usuario.
     * @param id
     */
    public UserNotFoundException(Long id) {
        super("User not found with id: " + id);
    }
}
