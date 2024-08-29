package pro.edwx.demo_hexagonal.domain.port.input.role;

import pro.edwx.demo_hexagonal.application.dto.role.RoleDTO;

public interface GetRoleUseCase {

    /**
     * Retrieves a role based on the provided data.
     *
     * @param id The ID of the role to retrieve
     * @return The role data
     * @throws IllegalArgumentException if the provided data is invalid
     */
    RoleDTO getRole(Long id);
}
