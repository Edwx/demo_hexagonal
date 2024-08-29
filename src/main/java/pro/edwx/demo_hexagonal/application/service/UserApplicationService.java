package pro.edwx.demo_hexagonal.application.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import pro.edwx.demo_hexagonal.application.dto.user.UserWithRolesDTO;
import pro.edwx.demo_hexagonal.application.dto.user.CreateUserRequestDTO;
import pro.edwx.demo_hexagonal.application.dto.user.UpdateUserRequestDTO;
import pro.edwx.demo_hexagonal.application.dto.user.UserDTO;
import pro.edwx.demo_hexagonal.domain.ApplicationService;
import pro.edwx.demo_hexagonal.domain.criteria.UserSearchCriteria;
import pro.edwx.demo_hexagonal.domain.exception.UserNotFoundException;
import pro.edwx.demo_hexagonal.shared.mapper.UserMapper;
import pro.edwx.demo_hexagonal.domain.model.User;
import pro.edwx.demo_hexagonal.domain.port.input.user.*;
import pro.edwx.demo_hexagonal.domain.port.output.UserRepository;
import pro.edwx.demo_hexagonal.domain.service.UserDomainService;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@ApplicationService
public class UserApplicationService implements CreateUserUseCase, GetUserUseCase, UpdateUserUseCase, DeleteUserUseCase, ListUsersUseCase, ListUsersWithRolesUseCase, CountUsersUseCase {

    private final UserDomainService userDomainService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserDTO createUser(CreateUserRequestDTO createUserRequestDTO) {
        User user = userMapper.createUserRequestDTOToUser(createUserRequestDTO);
        User createdUser = userDomainService.createUser(user);
        return userMapper.userToUserDTO(createdUser);
    }

    @Override
    @Transactional
    public UserDTO getUser(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        return userMapper.userToUserDTO(user);
    }

    @Override
    @Transactional
    public UserDTO updateUser(Long id, UpdateUserRequestDTO updateUserRequestDTO) {
        User existingUser = userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        User updatedUser = userMapper.updateUserFromDTO(existingUser, updateUserRequestDTO);
        User savedUser = userDomainService.updateUser(updatedUser);
        return userMapper.userToUserDTO(savedUser);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        userDomainService.deleteUser(user);
    }

    @Override
    public List<UserDTO> listUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
            .map(userMapper::userToUserDTO)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<UserWithRolesDTO> listUsersWithRoles(UserSearchCriteria criteria, int page, int size) {
        List<User> users = userRepository.findAllWithCriteria(criteria, page, size);
        return users.stream()
            .map(userMapper::userToUserWithRolesDTO)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public long countUsers(UserSearchCriteria criteria) {
        return userRepository.countWithCriteria(criteria);
    }
}