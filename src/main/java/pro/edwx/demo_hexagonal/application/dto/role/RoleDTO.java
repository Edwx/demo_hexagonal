package pro.edwx.demo_hexagonal.application.dto.role;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class RoleDTO {

    // Getters y Setters
    private Long id;
    private String name;
    private String description;

    // Constructores
    public RoleDTO() {
    }

    public RoleDTO(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    // toString method para facilitar el logging y debugging
    @Override
    public String toString() {
        return "RoleDTO{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            '}';
    }
}
