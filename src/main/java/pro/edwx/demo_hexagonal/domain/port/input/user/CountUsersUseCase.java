package pro.edwx.demo_hexagonal.domain.port.input.user;

import pro.edwx.demo_hexagonal.domain.criteria.UserSearchCriteria;

public interface CountUsersUseCase {
    long countUsers(UserSearchCriteria criteria);
}
