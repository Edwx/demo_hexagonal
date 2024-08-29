package pro.edwx.demo_hexagonal.infrastructure.adapter.input.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.edwx.demo_hexagonal.application.dto.auth.LoginRequestDTO;
import pro.edwx.demo_hexagonal.application.dto.auth.LoginResponseDTO;
import pro.edwx.demo_hexagonal.application.dto.user.CreateUserRequestDTO;
import pro.edwx.demo_hexagonal.application.dto.user.UserDTO;
import pro.edwx.demo_hexagonal.application.service.AuthApplicationService;
import pro.edwx.demo_hexagonal.application.service.UserApplicationService;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthRestController {

    private final AuthApplicationService authApplicationService;
    private final UserApplicationService userApplicationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        LoginResponseDTO login = authApplicationService.login(loginRequestDTO);
        return ResponseEntity.ok(login);
    }

    //register
    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@Valid @RequestBody CreateUserRequestDTO requestDTO) {
        UserDTO createdUser = userApplicationService.createUser(requestDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    /*@PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        authApplicationService.logout();
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDTO> refresh() {
        LoginResponseDTO login = authApplicationService.refresh();
        return ResponseEntity.ok(login);
    }*/
}
