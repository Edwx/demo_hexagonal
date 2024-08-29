package pro.edwx.demo_hexagonal.domain.port.output;

public interface PasswordEncoder {
    String encode(String rawPassword);
    boolean matches(String rawPassword, String encodedPassword);
}
