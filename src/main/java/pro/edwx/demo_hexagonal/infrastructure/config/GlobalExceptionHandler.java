package pro.edwx.demo_hexagonal.infrastructure.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pro.edwx.demo_hexagonal.application.dto.ErrorResponse;
import pro.edwx.demo_hexagonal.domain.exception.BusinessException;
import pro.edwx.demo_hexagonal.domain.exception.RoleNotFoundException;
import pro.edwx.demo_hexagonal.domain.exception.UserNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex) {
        logException(ex);
        return buildErrorResponse(ex, ex.getErrorCode(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex) {
        logException(ex);
        return buildErrorResponse(ex, "USER_NOT_FOUND", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleRoleNotFoundException(RoleNotFoundException ex) {
        logException(ex);
        return buildErrorResponse(ex, "ROLE_NOT_FOUND", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex) {
        logException(ex);
        return buildErrorResponse(ex, "INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(Exception ex, String errorCode, HttpStatus status) {
        ErrorResponse errorResponse = ErrorResponse.builder()
            .status(status.value())
            .error(status.getReasonPhrase())
            .errorCode(errorCode)
            .message(ex.getMessage())
            .build();
        return new ResponseEntity<>(errorResponse, status);
    }

    private void logException(Exception ex) {
        if (ex.getCause() != null) {
            logger.error("Exception occurred: {} with cause: {}", ex.getMessage(), ex.getCause().toString(), ex);
        } else {
            logger.error("Exception occurred: {}", ex.getMessage(), ex);
        }
    }

}
