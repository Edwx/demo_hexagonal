package pro.edwx.demo_hexagonal.domain.exception;

public class RoleNotFoundException extends RuntimeException {

    public RoleNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructor que acepta un mensaje de error y una causa.
     * @param message
     * @param cause
     */
    public RoleNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor que acepta un id de rol.
     * @param id
     */
    public RoleNotFoundException(Long id) {
        super("Role not found with id: " + id);
    }
}