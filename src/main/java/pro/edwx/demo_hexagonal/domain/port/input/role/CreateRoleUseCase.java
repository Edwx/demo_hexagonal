package pro.edwx.demo_hexagonal.domain.port.input.role;

import pro.edwx.demo_hexagonal.application.dto.role.CreateRoleRequestDTO;
import pro.edwx.demo_hexagonal.application.dto.role.RoleDTO;

public interface CreateRoleUseCase {

    /**
     * Creates a role based on the provided data.
     *
     * @param createRoleRequestDTO The data for creating a role
     * @return The created role data
     * @throws IllegalArgumentException if the provided data is invalid
     */
    RoleDTO createRole(CreateRoleRequestDTO createRoleRequestDTO);
}
