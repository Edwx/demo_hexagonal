package pro.edwx.demo_hexagonal.domain.model;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Role {

    // Getters y Setters
    private Long id;
    private String name;
    private String description;

    @Builder.Default
    private Set<User> users = new HashSet<>();

    public Role(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Métodos de utilidad
    public void addUser(User user) {
        this.users.add(user);
        user.getRoles().add(this);
    }

    public void removeUser(User user) {
        this.users.remove(user);
        user.getRoles().remove(this);
    }

    // Equals y HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role role = (Role) o;
        return getId() != null && getId().equals(role.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
