package pro.edwx.demo_hexagonal.shared.mapper;

import org.springframework.stereotype.Component;
import pro.edwx.demo_hexagonal.application.dto.user.UserWithRolesDTO;
import pro.edwx.demo_hexagonal.application.dto.user.CreateUserRequestDTO;
import pro.edwx.demo_hexagonal.application.dto.user.UpdateUserRequestDTO;
import pro.edwx.demo_hexagonal.application.dto.user.UserDTO;
import pro.edwx.demo_hexagonal.domain.model.User;
import pro.edwx.demo_hexagonal.infrastructure.entity.UserEntity;

import java.util.stream.Collectors;

@Component
public class UserMapper {

    private final RoleMapper roleMapper;

    public UserMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    public UserDTO userToUserDTO(User user) {
        if (user == null) {
            return null;
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setRoles(user.getRoles().stream()
            .map(roleMapper::roleToRoleDTO)
            .collect(Collectors.toSet()));
        return userDTO;
    }

    public User userDTOToUser(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }

        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setRoles(userDTO.getRoles().stream()
            .map(roleMapper::roleDTOToRole)
            .collect(Collectors.toSet()));
        return user;
    }

    public User createUserRequestDTOToUser(CreateUserRequestDTO createUserRequestDTO) {
        if (createUserRequestDTO == null) {
            return null;
        }

        User user = new User();
        user.setUsername(createUserRequestDTO.getUsername());
        user.setEmail(createUserRequestDTO.getEmail());
        user.setPassword(createUserRequestDTO.getPassword());
        user.setRoleIds(createUserRequestDTO.getRoleIds());
        return user;
    }

    public User updateUserFromDTO(User user, UpdateUserRequestDTO updateUserRequestDTO) {
        if (updateUserRequestDTO.getUsername() != null) {
            user.setUsername(updateUserRequestDTO.getUsername());
        }
        if (updateUserRequestDTO.getEmail() != null) {
            user.setEmail(updateUserRequestDTO.getEmail());
        }
        if (updateUserRequestDTO.getPassword() != null) {
            user.setPassword(updateUserRequestDTO.getPassword());
        }
        if (updateUserRequestDTO.getRoleIds() != null) {
            user.setRoleIds(updateUserRequestDTO.getRoleIds());
        }
        return user;
    }

    public UserEntity userToUserEntity(User user) {
        if (user == null) {
            return null;
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setUsername(user.getUsername());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());
        userEntity.setRoles(user.getRoles().stream()
            .map(roleMapper::roleToRoleEntity)
            .collect(Collectors.toSet()));
        return userEntity;
    }

    public User userEntityToUser(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }

        User user = new User();
        user.setId(userEntity.getId());
        user.setUsername(userEntity.getUsername());
        user.setEmail(userEntity.getEmail());
        user.setPassword(userEntity.getPassword());
        user.setRoles(userEntity.getRoles().stream()
            .map(roleMapper::roleEntityToRole)
            .collect(Collectors.toSet()));
        return user;
    }

    public UserWithRolesDTO userToUserWithRolesDTO(User user) {
        UserWithRolesDTO dto = new UserWithRolesDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRoles(user.getRoles().stream()
            .map(role -> role.getName())
            .collect(Collectors.joining(", ")));
        return dto;
    }
}
