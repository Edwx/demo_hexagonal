package pro.edwx.demo_hexagonal.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import pro.edwx.demo_hexagonal.infrastructure.entity.UserEntity;

import java.util.Optional;

@Repository
public interface JpaUserRepository extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByEmail(String email);
}
