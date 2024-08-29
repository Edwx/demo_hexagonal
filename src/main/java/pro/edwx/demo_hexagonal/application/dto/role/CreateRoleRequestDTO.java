package pro.edwx.demo_hexagonal.application.dto.role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateRoleRequestDTO {

    // Getters y Setters
    @NotBlank(message = "Role name is required")
    @Size(min = 3, max = 50, message = "Role name must be between 3 and 50 characters")
    private String name;

    @Size(max = 255, message = "Description cannot exceed 255 characters")
    private String description;

    // Constructores
    public CreateRoleRequestDTO() {
    }

    public CreateRoleRequestDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // toString method para facilitar el logging y debugging
    @Override
    public String toString() {
        return "CreateRoleRequestDTO{" +
            "name='" + name + '\'' +
            ", description='" + description + '\'' +
            '}';
    }
}
