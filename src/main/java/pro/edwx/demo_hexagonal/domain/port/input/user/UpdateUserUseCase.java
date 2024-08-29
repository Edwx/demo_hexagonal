package pro.edwx.demo_hexagonal.domain.port.input.user;

import pro.edwx.demo_hexagonal.application.dto.user.UpdateUserRequestDTO;
import pro.edwx.demo_hexagonal.application.dto.user.UserDTO;

public interface UpdateUserUseCase {

    /**
     * Updates a user based on the provided data.
     *
     * @param id The ID of the user to update
     * @param updateUserRequestDTO The data for updating a user
     * @return The updated user data
     * @throws IllegalArgumentException if the provided data is invalid
     */
    UserDTO updateUser(Long id, UpdateUserRequestDTO updateUserRequestDTO);
}
