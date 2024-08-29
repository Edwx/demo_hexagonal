package pro.edwx.demo_hexagonal.application.dto.auth;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class LoginResponseDTO {
    private String token;
    private String type = "Bearer";
    private String username;

}
