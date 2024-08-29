package pro.edwx.demo_hexagonal.domain.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessException extends RuntimeException {

    /**
     * -- GETTER --
     *  Obtiene el código de error asociado con esta excepción.
     *
     *
     * -- SETTER --
     *  Establece el código de error para esta excepción.
     *
     @return el código de error
      * @param errorCode el código de error a establecer
     */
    private String errorCode;

    /**
     * Constructor que acepta un mensaje de error.
     *
     * @param message el mensaje de error
     */
    public BusinessException(String message) {
        super(message);
    }

    /**
     * Constructor que acepta un mensaje de error y una causa.
     *
     * @param message el mensaje de error
     * @param cause la causa de la excepción
     */
    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor que acepta un mensaje de error y un código de error.
     *
     * @param message el mensaje de error
     * @param errorCode el código de error
     */
    public BusinessException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    /**
     * Constructor que acepta un mensaje de error, una causa y un código de error.
     *
     * @param message el mensaje de error
     * @param cause la causa de la excepción
     * @param errorCode el código de error
     */
    public BusinessException(String message, Throwable cause, String errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return "BusinessException{" +
            "message='" + getMessage() + '\'' +
            ", errorCode='" + errorCode + '\'' +
            '}';
    }
}
