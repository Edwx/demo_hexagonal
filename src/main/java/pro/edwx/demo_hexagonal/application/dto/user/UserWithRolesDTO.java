package pro.edwx.demo_hexagonal.application.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserWithRolesDTO {
    private Long id;
    private String username;
    private String email;
    private String roles;  // Roles separados por comas

}
