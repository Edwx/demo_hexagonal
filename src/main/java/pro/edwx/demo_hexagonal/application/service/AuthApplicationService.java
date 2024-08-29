package pro.edwx.demo_hexagonal.application.service;

import lombok.AllArgsConstructor;
import pro.edwx.demo_hexagonal.application.dto.auth.LoginRequestDTO;
import pro.edwx.demo_hexagonal.application.dto.auth.LoginResponseDTO;
import pro.edwx.demo_hexagonal.domain.ApplicationService;
import pro.edwx.demo_hexagonal.domain.exception.BusinessException;
import pro.edwx.demo_hexagonal.domain.exception.UserNotFoundException;
import pro.edwx.demo_hexagonal.domain.model.User;
import pro.edwx.demo_hexagonal.domain.port.output.PasswordEncoder;
import pro.edwx.demo_hexagonal.domain.port.output.TokenProvider;
import pro.edwx.demo_hexagonal.domain.port.output.UserRepository;

@AllArgsConstructor
@ApplicationService
public class AuthApplicationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        User user = userRepository.findByUsername(loginRequestDTO.getUsername())
            .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (!passwordEncoder.matches(loginRequestDTO.getPassword(), user.getPassword())) {
            throw new BusinessException("Invalid password");
        }

        String jwtToken = tokenProvider.generateToken(user);
        return LoginResponseDTO.builder()
            .token(jwtToken)
            .type("Bearer")
            .username(user.getUsername())
            .build();
    }
}
