package pro.edwx.demo_hexagonal.shared.mapper;

import org.springframework.stereotype.Component;
import pro.edwx.demo_hexagonal.application.dto.role.CreateRoleRequestDTO;
import pro.edwx.demo_hexagonal.application.dto.role.RoleDTO;
import pro.edwx.demo_hexagonal.application.dto.role.UpdateRoleRequestDTO;
import pro.edwx.demo_hexagonal.domain.model.Role;
import pro.edwx.demo_hexagonal.infrastructure.entity.RoleEntity;

@Component
public class RoleMapper {

    public RoleDTO roleToRoleDTO(Role role) {
        if (role == null) {
            return null;
        }

        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setName(role.getName());
        roleDTO.setDescription(role.getDescription());
        return roleDTO;
    }

    public Role roleDTOToRole(RoleDTO roleDTO) {
        if (roleDTO == null) {
            return null;
        }

        Role role = new Role();
        role.setId(roleDTO.getId());
        role.setName(roleDTO.getName());
        role.setDescription(roleDTO.getDescription());
        return role;
    }

    public Role createRoleRequestDTOToRole(CreateRoleRequestDTO createRoleRequestDTO) {
        if (createRoleRequestDTO == null) {
            return null;
        }

        Role role = new Role();
        role.setName(createRoleRequestDTO.getName());
        role.setDescription(createRoleRequestDTO.getDescription());
        return role;
    }

    public Role updateRoleFromDTO(Role role, UpdateRoleRequestDTO updateRoleRequestDTO) {
        if (updateRoleRequestDTO.getName() != null) {
            role.setName(updateRoleRequestDTO.getName());
        }
        if (updateRoleRequestDTO.getDescription() != null) {
            role.setDescription(updateRoleRequestDTO.getDescription());
        }
        return role;
    }

    public RoleEntity roleToRoleEntity(Role role) {
        if (role == null) {
            return null;
        }

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(role.getId());
        roleEntity.setName(role.getName());
        roleEntity.setDescription(role.getDescription());
        return roleEntity;
    }

    public Role roleEntityToRole(RoleEntity roleEntity) {
        if (roleEntity == null) {
            return null;
        }

        Role role = new Role();
        role.setId(roleEntity.getId());
        role.setName(roleEntity.getName());
        role.setDescription(roleEntity.getDescription());
        return role;
    }
}
