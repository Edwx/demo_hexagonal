package pro.edwx.demo_hexagonal.infrastructure.specification;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import pro.edwx.demo_hexagonal.domain.criteria.UserSearchCriteria;
import pro.edwx.demo_hexagonal.infrastructure.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class UserSpecification {

    public static Specification<UserEntity> withCriteria(UserSearchCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (criteria.getUsername() != null && !criteria.getUsername().isEmpty()) {
                predicates.add(cb.like(root.get("username"), "%" + criteria.getUsername() + "%"));
            }

            if (criteria.getEmail() != null && !criteria.getEmail().isEmpty()) {
                predicates.add(cb.like(root.get("email"), "%" + criteria.getEmail() + "%"));
            }

            if (criteria.getRoleName() != null && !criteria.getRoleName().isEmpty()) {
                Join<Object, Object> roleJoin = root.join("roles");
                predicates.add(cb.equal(roleJoin.get("name"), criteria.getRoleName()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
