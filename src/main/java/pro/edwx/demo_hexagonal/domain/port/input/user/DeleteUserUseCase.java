package pro.edwx.demo_hexagonal.domain.port.input.user;

public interface DeleteUserUseCase {

    /**
     * Deletes a user based on the provided data.
     *
     * @param id The ID of the user to delete
     * @throws IllegalArgumentException if the provided data is invalid
     */
    void deleteUser(Long id);
}
