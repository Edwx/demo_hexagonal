package pro.edwx.demo_hexagonal.domain.port.input.user;

import pro.edwx.demo_hexagonal.application.dto.user.UserDTO;

import java.util.List;

public interface ListUsersUseCase {

    /**
     * Retrieves all users.
     * @return The list of users
     */
    List<UserDTO> listUsers();
}
