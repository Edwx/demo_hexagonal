package pro.edwx.demo_hexagonal.infrastructure.adapter.output;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pro.edwx.demo_hexagonal.domain.port.output.PasswordEncoder;

@Component
@AllArgsConstructor
public class SpringPasswordEncoder implements PasswordEncoder {
    private final org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

    @Override
    public String encode(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
