package pro.edwx.demo_hexagonal.shared.mapper;

import org.springframework.stereotype.Component;
import pro.edwx.demo_hexagonal.application.dto.user.UserWithRolesDTO;
import pro.edwx.demo_hexagonal.application.dto.user.CreateUserRequestDTO;
import pro.edwx.demo_hexagonal.application.dto.user.UpdateUserRequestDTO;
import pro.edwx.demo_hexagonal.application.dto.user.UserDTO;
import pro.edwx.demo_hexagonal.domain.model.Role;
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

        return UserDTO.builder()
            .id(user.getId())
            .username(user.getUsername())
            .email(user.getEmail())
            .roles(user.getRoles().stream()
                .map(roleMapper::roleToRoleDTO)
                .collect(Collectors.toSet()))
            .build();
    }

    public User userDTOToUser(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }

        return User.builder()
            .id(userDTO.getId())
            .username(userDTO.getUsername())
            .email(userDTO.getEmail())
            .roles(userDTO.getRoles().stream()
                .map(roleMapper::roleDTOToRole)
                .collect(Collectors.toSet()))
            .build();
    }

    public User createUserRequestDTOToUser(CreateUserRequestDTO createUserRequestDTO) {
        if (createUserRequestDTO == null) {
            return null;
        }

        return User.builder()
            .username(createUserRequestDTO.getUsername())
            .email(createUserRequestDTO.getEmail())
            .password(createUserRequestDTO.getPassword())
            .roleIds(createUserRequestDTO.getRoleIds())
            .build();
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

        UserEntity userEntity = UserEntity.builder()
            .id(user.getId())
            .username(user.getUsername())
            .email(user.getEmail())
            .password(user.getPassword())
            .build();

        userEntity.setRoles(
            user.getRoles().stream()
                .map(roleMapper::roleToRoleEntity)
                .collect(Collectors.toSet())
        );
        return userEntity;
    }

    public User userEntityToUser(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }

        User user = User.builder()
            .id(userEntity.getId())
            .username(userEntity.getUsername())
            .email(userEntity.getEmail())
            .password(userEntity.getPassword())
            .build();

        user.setRoles(
            userEntity.getRoles().stream()
                .map(roleMapper::roleEntityToRole)
                .collect(Collectors.toSet())
        );
        return user;
    }

    public UserWithRolesDTO userToUserWithRolesDTO(User user) {
        return UserWithRolesDTO.builder()
            .id(user.getId())
            .username(user.getUsername())
            .email(user.getEmail())
            .roles(user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.joining(", "))
            )
            .build();
    }
}
