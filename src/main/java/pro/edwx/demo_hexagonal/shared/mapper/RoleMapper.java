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

        return RoleDTO.builder()
            .id(role.getId())
            .name(role.getName())
            .description(role.getDescription())
            .build();
    }

    public Role roleDTOToRole(RoleDTO roleDTO) {
        if (roleDTO == null) {
            return null;
        }

        return Role.builder()
            .id(roleDTO.getId())
            .name(roleDTO.getName())
            .description(roleDTO.getDescription())
            .build();
    }

    public Role createRoleRequestDTOToRole(CreateRoleRequestDTO createRoleRequestDTO) {
        if (createRoleRequestDTO == null) {
            return null;
        }

        return Role.builder()
            .name(createRoleRequestDTO.getName())
            .description(createRoleRequestDTO.getDescription())
            .build();
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

        return RoleEntity.builder()
            .id(role.getId())
            .name(role.getName())
            .description(role.getDescription())
            .build();
    }

    public Role roleEntityToRole(RoleEntity roleEntity) {
        if (roleEntity == null) {
            return null;
        }

        return Role.builder()
            .id(roleEntity.getId())
            .name(roleEntity.getName())
            .description(roleEntity.getDescription())
            .build();
    }
}
