package pro.edwx.demo_hexagonal.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ErrorResponse {

    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String errorCode;
    private String message;

    public ErrorResponse(int status, String error, String errorCode, String message) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.error = error;
        this.errorCode = errorCode;
        this.message = message;
    }

}