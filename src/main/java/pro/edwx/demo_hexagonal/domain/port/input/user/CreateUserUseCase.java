package pro.edwx.demo_hexagonal.domain.port.input.user;

import pro.edwx.demo_hexagonal.application.dto.user.CreateUserRequestDTO;
import pro.edwx.demo_hexagonal.application.dto.user.UserDTO;

public interface CreateUserUseCase {

    /**
     * Creates a new user based on the provided data.
     *
     * @param createUserRequestDTO The data for creating a new user
     * @return The created user data
     * @throws IllegalArgumentException if the provided data is invalid
     */
    UserDTO createUser(CreateUserRequestDTO createUserRequestDTO);
}
