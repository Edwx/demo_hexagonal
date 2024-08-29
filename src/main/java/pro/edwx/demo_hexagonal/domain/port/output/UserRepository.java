package pro.edwx.demo_hexagonal.domain.port.output;

import pro.edwx.demo_hexagonal.domain.criteria.UserSearchCriteria;
import pro.edwx.demo_hexagonal.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findById(Long id);

    List<User> findAll();

    void delete(User user);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    List<User> findAllWithCriteria(UserSearchCriteria criteria, int page, int size);

    long countWithCriteria(UserSearchCriteria criteria);
}