package pro.edwx.demo_hexagonal.domain.model;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User {

    private Long id;
    private String username;
    private String email;
    private String password;

    @Builder.Default
    private Set<Role> roles = new HashSet<>();

    @Builder.Default
    private Set<Long> roleIds = new HashSet<>();

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public void addRole(Role role) {
        this.roles.add(role);
        this.roleIds.add(role.getId());
    }

    public void removeRole(Role role) {
        this.roles.remove(role);
        this.roleIds.remove(role.getId());
    }

    public boolean hasRole(String roleName) {
        return this.roles.stream().anyMatch(role -> role.getName().equals(roleName));
    }
}
