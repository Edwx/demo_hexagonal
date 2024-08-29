package pro.edwx.demo_hexagonal.domain.port.input.user;

import pro.edwx.demo_hexagonal.application.dto.user.UserDTO;
import pro.edwx.demo_hexagonal.domain.model.User;

import java.util.Set;

public interface GetUserUseCase {

    /**
     * Retrieves a user based on the provided data.
     *
     * @param id The ID of the user to retrieve
     * @return The user data
     * @throws IllegalArgumentException if the provided data is invalid
     */
    UserDTO getUser(Long id);
}
