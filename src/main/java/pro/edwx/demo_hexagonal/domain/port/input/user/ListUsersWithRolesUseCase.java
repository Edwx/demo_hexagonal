package pro.edwx.demo_hexagonal.domain.port.input.user;

import pro.edwx.demo_hexagonal.application.dto.user.UserWithRolesDTO;
import pro.edwx.demo_hexagonal.domain.criteria.UserSearchCriteria;

import java.util.List;

public interface ListUsersWithRolesUseCase {

    /**
     * Retrieves all users with their roles.
     *
     * @param criteria The search criteria
     * @param page The page number
     * @param size The page size
     * @return The list of users with their roles
     */
    List<UserWithRolesDTO> listUsersWithRoles(UserSearchCriteria criteria, int page, int size);
}
