package pro.edwx.demo_hexagonal.application.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDTO {
    private String username;
    private String password;
}
