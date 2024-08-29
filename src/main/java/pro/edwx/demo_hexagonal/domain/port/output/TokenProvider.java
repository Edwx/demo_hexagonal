package pro.edwx.demo_hexagonal.domain.port.output;

import pro.edwx.demo_hexagonal.domain.model.User;

public interface TokenProvider {
    String generateToken(User user);
}
