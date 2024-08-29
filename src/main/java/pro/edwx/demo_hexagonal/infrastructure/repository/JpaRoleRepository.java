package pro.edwx.demo_hexagonal.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.edwx.demo_hexagonal.infrastructure.entity.RoleEntity;

import java.util.Optional;

@Repository
public interface JpaRoleRepository extends JpaRepository<RoleEntity, Long> {

    boolean existsByName(String name);

    Optional<RoleEntity> findByName(String name);
}
