package pro.edwx.demo_hexagonal.domain.port.input.role;

import pro.edwx.demo_hexagonal.application.dto.role.RoleDTO;

import java.util.List;

public interface ListRolesUseCase {

    /**
     * Retrieves all roles.
     *
     * @return The list of roles
     */
    List<RoleDTO> listRoles();
}
