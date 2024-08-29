package pro.edwx.demo_hexagonal.application.dto.user;

import lombok.*;
import pro.edwx.demo_hexagonal.application.dto.role.RoleDTO;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class UserDTO {

    // Getters and Setters
    private Long id;
    private String username;
    private String email;

    @Builder.Default
    private Set<RoleDTO> roles = new HashSet<>();

    public UserDTO(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    // Helper methods
    public void addRole(RoleDTO role) {
        this.roles.add(role);
    }

    public void removeRole(RoleDTO role) {
        this.roles.remove(role);
    }
}
