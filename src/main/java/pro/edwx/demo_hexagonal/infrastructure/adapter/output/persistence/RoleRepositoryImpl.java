package pro.edwx.demo_hexagonal.infrastructure.adapter.output.persistence;

import org.springframework.stereotype.Repository;
import pro.edwx.demo_hexagonal.shared.mapper.RoleMapper;
import pro.edwx.demo_hexagonal.domain.model.Role;
import pro.edwx.demo_hexagonal.domain.port.output.RoleRepository;
import pro.edwx.demo_hexagonal.infrastructure.entity.RoleEntity;
import pro.edwx.demo_hexagonal.infrastructure.repository.JpaRoleRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

    private final JpaRoleRepository jpaRoleRepository;
    private final RoleMapper roleMapper;

    public RoleRepositoryImpl(JpaRoleRepository jpaRoleRepository, RoleMapper roleMapper) {
        this.jpaRoleRepository = jpaRoleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public Role save(Role role) {
        RoleEntity roleEntity = roleMapper.roleToRoleEntity(role);
        RoleEntity savedEntity = jpaRoleRepository.save(roleEntity);
        return roleMapper.roleEntityToRole(savedEntity);
    }

    @Override
    public Optional<Role> findById(Long id) {
        return jpaRoleRepository.findById(id)
            .map(roleMapper::roleEntityToRole);
    }

    @Override
    public List<Role> findAll() {
        return jpaRoleRepository.findAll().stream()
            .map(roleMapper::roleEntityToRole)
            .collect(Collectors.toList());
    }

    @Override
    public void delete(Role role) {
        RoleEntity roleEntity = roleMapper.roleToRoleEntity(role);
        jpaRoleRepository.delete(roleEntity);
    }

    @Override
    public boolean existsByName(String name) {
        return jpaRoleRepository.existsByName(name);
    }

    @Override
    public Optional<Role> findByName(String name) {
        return jpaRoleRepository.findByName(name)
            .map(roleMapper::roleEntityToRole);
    }
}
