package pro.edwx.demo_hexagonal.domain.port.input.role;

import pro.edwx.demo_hexagonal.application.dto.role.RoleDTO;
import pro.edwx.demo_hexagonal.application.dto.role.UpdateRoleRequestDTO;

public interface UpdateRoleUseCase {

    /**
     * Updates a role based on the provided data.
     *
     * @param id The ID of the role to update
     * @param updateRoleRequestDTO The data for updating a role
     * @return The updated role data
     * @throws IllegalArgumentException if the provided data is invalid
     */
    RoleDTO updateRole(Long id, UpdateRoleRequestDTO updateRoleRequestDTO);
}
