package pro.edwx.demo_hexagonal.application.dto.user;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserWithRolesDTO {
    private Long id;
    private String username;
    private String email;
    private String roles;  // Roles separados por comas

}
