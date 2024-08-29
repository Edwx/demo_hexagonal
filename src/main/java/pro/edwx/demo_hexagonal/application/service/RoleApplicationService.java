package pro.edwx.demo_hexagonal.application.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import pro.edwx.demo_hexagonal.application.dto.role.CreateRoleRequestDTO;
import pro.edwx.demo_hexagonal.application.dto.role.RoleDTO;
import pro.edwx.demo_hexagonal.application.dto.role.UpdateRoleRequestDTO;
import pro.edwx.demo_hexagonal.domain.ApplicationService;
import pro.edwx.demo_hexagonal.domain.exception.RoleNotFoundException;
import pro.edwx.demo_hexagonal.shared.mapper.RoleMapper;
import pro.edwx.demo_hexagonal.domain.model.Role;
import pro.edwx.demo_hexagonal.domain.port.input.role.*;
import pro.edwx.demo_hexagonal.domain.port.output.RoleRepository;
import pro.edwx.demo_hexagonal.domain.service.RoleDomainService;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@ApplicationService
public class RoleApplicationService implements CreateRoleUseCase, GetRoleUseCase, UpdateRoleUseCase, DeleteRoleUseCase, ListRolesUseCase {

    private final RoleDomainService roleDomainService;
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    @Transactional
    public RoleDTO createRole(CreateRoleRequestDTO createRoleRequestDTO) {
        Role role = roleMapper.createRoleRequestDTOToRole(createRoleRequestDTO);
        Role createdRole = roleDomainService.createRole(role);
        return roleMapper.roleToRoleDTO(createdRole);
    }

    @Override
    @Transactional
    public RoleDTO getRole(Long id) {
        Role role = roleRepository.findById(id)
            .orElseThrow(() -> new RoleNotFoundException("Role not found with id: " + id));
        return roleMapper.roleToRoleDTO(role);
    }

    @Override
    @Transactional
    public RoleDTO updateRole(Long id, UpdateRoleRequestDTO updateRoleRequestDTO) {
        Role existingRole = roleRepository.findById(id)
            .orElseThrow(() -> new RoleNotFoundException("Role not found with id: " + id));
        Role updatedRole = roleMapper.updateRoleFromDTO(existingRole, updateRoleRequestDTO);
        Role savedRole = roleDomainService.updateRole(updatedRole);
        return roleMapper.roleToRoleDTO(savedRole);
    }

    @Override
    @Transactional
    public void deleteRole(Long id) {
        Role role = roleRepository.findById(id)
            .orElseThrow(() -> new RoleNotFoundException("Role not found with id: " + id));
        roleDomainService.deleteRole(role);
    }

    @Override
    @Transactional
    public List<RoleDTO> listRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream()
            .map(roleMapper::roleToRoleDTO)
            .collect(Collectors.toList());
    }
}