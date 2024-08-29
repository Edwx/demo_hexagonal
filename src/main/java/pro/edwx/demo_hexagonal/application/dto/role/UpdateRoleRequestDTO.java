package pro.edwx.demo_hexagonal.application.dto.role;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateRoleRequestDTO {

    // Getters y Setters
    @Size(min = 3, max = 50, message = "Role name must be between 3 and 50 characters")
    private String name;

    @Size(max = 255, message = "Description cannot exceed 255 characters")
    private String description;

    // Constructores
    public UpdateRoleRequestDTO() {
    }

    public UpdateRoleRequestDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // toString method para facilitar el logging y debugging
    @Override
    public String toString() {
        return "UpdateRoleRequestDTO{" +
            "name='" + name + '\'' +
            ", description='" + description + '\'' +
            '}';
    }
}
