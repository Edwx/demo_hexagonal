package pro.edwx.demo_hexagonal.domain.port.output;

import pro.edwx.demo_hexagonal.domain.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleRepository {

    Role save(Role role);

    Optional<Role> findById(Long id);

    List<Role> findAll();

    void delete(Role role);

    boolean existsByName(String name);

    Optional<Role> findByName(String name);
}
