package pro.edwx.demo_hexagonal.infrastructure.adapter.output.persistence;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import pro.edwx.demo_hexagonal.domain.criteria.UserSearchCriteria;
import pro.edwx.demo_hexagonal.infrastructure.specification.UserSpecification;
import pro.edwx.demo_hexagonal.shared.mapper.UserMapper;
import pro.edwx.demo_hexagonal.domain.model.User;
import pro.edwx.demo_hexagonal.domain.port.output.UserRepository;
import pro.edwx.demo_hexagonal.infrastructure.entity.UserEntity;
import pro.edwx.demo_hexagonal.infrastructure.repository.JpaUserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;
    private final UserMapper userMapper;

    public UserRepositoryImpl(JpaUserRepository jpaUserRepository, UserMapper userMapper) {
        this.jpaUserRepository = jpaUserRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = userMapper.userToUserEntity(user);
        UserEntity savedEntity = jpaUserRepository.save(userEntity);
        return userMapper.userEntityToUser(savedEntity);
    }

    @Override
    public Optional<User> findById(Long id) {
        return jpaUserRepository.findById(id)
            .map(userMapper::userEntityToUser);
    }

    @Override
    public List<User> findAll() {
        return jpaUserRepository.findAll().stream()
            .map(userMapper::userEntityToUser)
            .collect(Collectors.toList());
    }

    @Override
    public void delete(User user) {
        UserEntity userEntity = userMapper.userToUserEntity(user);
        jpaUserRepository.delete(userEntity);
    }

    @Override
    public boolean existsByUsername(String username) {
        return jpaUserRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaUserRepository.existsByEmail(email);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return jpaUserRepository.findByUsername(username)
            .map(userMapper::userEntityToUser);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaUserRepository.findByEmail(email)
            .map(userMapper::userEntityToUser);
    }

    @Override
    public List<User> findAllWithCriteria(UserSearchCriteria criteria, int page, int size) {
        Specification<UserEntity> spec = UserSpecification.withCriteria(criteria);
        return jpaUserRepository.findAll(spec, PageRequest.of(page, size))
            .stream()
            .map(userMapper::userEntityToUser)
            .collect(Collectors.toList());
    }

    @Override
    public long countWithCriteria(UserSearchCriteria criteria) {
        Specification<UserEntity> spec = UserSpecification.withCriteria(criteria);
        return jpaUserRepository.count(spec);
    }
}