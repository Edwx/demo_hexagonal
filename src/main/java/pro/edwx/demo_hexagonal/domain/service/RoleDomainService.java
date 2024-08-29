package pro.edwx.demo_hexagonal.domain.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pro.edwx.demo_hexagonal.domain.exception.BusinessException;
import pro.edwx.demo_hexagonal.domain.model.Role;
import pro.edwx.demo_hexagonal.domain.port.output.RoleRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class RoleDomainService {

    private final RoleRepository roleRepository;

    public Role createRole(Role role) {
        validateNewRole(role);
        return roleRepository.save(role);
    }

    public Role updateRole(Role role) {
        validateExistingRole(role);
        return roleRepository.save(role);
    }

    public void deleteRole(Role role) {
        if (isRoleInUse(role)) {
            throw new BusinessException("Cannot delete role as it is assigned to users", "ROLE_003");
        }
        roleRepository.delete(role);
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role getRoleById(Long id) {
        return roleRepository.findById(id)
            .orElseThrow(() -> new BusinessException("Role not found with id: " + id, "ROLE_001"));
    }

    public Role getRoleByName(String name) {
        return roleRepository.findByName(name)
            .orElseThrow(() -> new BusinessException("Role not found with name: " + name, "ROLE_002"));
    }

    private void validateNewRole(Role role) {
        if (roleRepository.existsByName(role.getName())) {
            throw new BusinessException("Role name already exists", "ROLE_004");
        }
    }

    private void validateExistingRole(Role role) {
        Role existingRole = roleRepository.findById(role.getId())
            .orElseThrow(() -> new BusinessException("Role not found", "ROLE_001"));
        if (!existingRole.getName().equals(role.getName()) && roleRepository.existsByName(role.getName())) {
            throw new BusinessException("Role name already exists", "ROLE_004");
        }
    }

    private boolean isRoleInUse(Role role) {
        // Esta lógica dependerá de cómo se implementa la relación entre usuarios y roles
        // Por ejemplo, podrías tener un método en el repositorio para verificar esto
        return !role.getUsers().isEmpty();
    }

    public boolean isRoleNameAvailable(String name) {
        return !roleRepository.existsByName(name);
    }
}