package pro.edwx.demo_hexagonal.domain.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pro.edwx.demo_hexagonal.domain.exception.BusinessException;
import pro.edwx.demo_hexagonal.domain.model.Role;
import pro.edwx.demo_hexagonal.domain.model.User;
import pro.edwx.demo_hexagonal.domain.port.output.PasswordEncoder;
import pro.edwx.demo_hexagonal.domain.port.output.RoleRepository;
import pro.edwx.demo_hexagonal.domain.port.output.UserRepository;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserDomainService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public User createUser(User user) {
        validateNewUser(user);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Set<Role> roles = getRoles(user.getRoleIds());
        user.setRoles(roles);

        return userRepository.save(user);
    }

    public User updateUser(User user) {
        validateExistingUser(user);
        Set<Role> roles = getRoles(user.getRoleIds());
        user.setRoles(roles);
        return userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public User addRoleToUser(User user, Role role) {
        if (!user.getRoles().contains(role)) {
            user.addRole(role);
            return userRepository.save(user);
        }
        return user;
    }

    public User removeRoleFromUser(User user, Role role) {
        if (user.getRoles().contains(role)) {
            user.removeRole(role);
            return userRepository.save(user);
        }
        return user;
    }

    private void validateNewUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new BusinessException("Username already exists", "USER_001");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new BusinessException("Email already exists", "USER_002");
        }
    }

    private void validateExistingUser(User user) {
        User existingUser = userRepository.findById(user.getId())
            .orElseThrow(() -> new BusinessException("User not found", "USER_003"));
        if (!existingUser.getUsername().equals(user.getUsername()) && userRepository.existsByUsername(user.getUsername())) {
            throw new BusinessException("Username already exists", "USER_001");
        }
        if (!existingUser.getEmail().equals(user.getEmail()) && userRepository.existsByEmail(user.getEmail())) {
            throw new BusinessException("Email already exists", "USER_002");
        }
    }

    private Set<Role> getRoles(Set<Long> roleIds) {
        return roleIds.stream()
            .map(roleId -> roleRepository.findById(roleId)
                .orElseThrow(() -> new BusinessException("Role not found with id: " + roleId, "ROLE_001")))
            .collect(Collectors.toSet());
    }

    public boolean isUsernameAvailable(String username) {
        return !userRepository.existsByUsername(username);
    }

    public boolean isEmailAvailable(String email) {
        return !userRepository.existsByEmail(email);
    }

    public User changeUserPassword(User user, String newPassword) {
        // Aquí podrías agregar lógica adicional, como validar la complejidad de la contraseña
        user.setPassword(newPassword);
        return userRepository.save(user);
    }
}