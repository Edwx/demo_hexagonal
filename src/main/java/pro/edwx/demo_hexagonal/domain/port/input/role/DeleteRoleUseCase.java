package pro.edwx.demo_hexagonal.domain.port.input.role;

public interface DeleteRoleUseCase {

    /**
     * Deletes a role based on the provided data.
     *
     * @param id The ID of the role to delete
     * @throws IllegalArgumentException if the provided data is invalid
     */
    void deleteRole(Long id);
}
