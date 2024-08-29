package pro.edwx.demo_hexagonal.infrastructure.adapter.input.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.edwx.demo_hexagonal.application.dto.PagedResponse;
import pro.edwx.demo_hexagonal.application.dto.user.UserWithRolesDTO;
import pro.edwx.demo_hexagonal.application.dto.user.CreateUserRequestDTO;
import pro.edwx.demo_hexagonal.application.dto.user.UpdateUserRequestDTO;
import pro.edwx.demo_hexagonal.application.dto.user.UserDTO;
import pro.edwx.demo_hexagonal.application.service.UserApplicationService;
import pro.edwx.demo_hexagonal.domain.criteria.UserSearchCriteria;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private final UserApplicationService userApplicationService;

    /*@PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody CreateUserRequestDTO requestDTO) {
        UserDTO createdUser = userApplicationService.createUser(requestDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }*/

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        UserDTO user = userApplicationService.getUser(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @Valid @RequestBody UpdateUserRequestDTO requestDTO) {
        UserDTO updatedUser = userApplicationService.updateUser(id, requestDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userApplicationService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> listUsers() {
        List<UserDTO> users = userApplicationService.listUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/with-roles")
    public ResponseEntity<PagedResponse<UserWithRolesDTO>> listUsersWithRoles(
        @ModelAttribute UserSearchCriteria criteria,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size) {

        List<UserWithRolesDTO> users = userApplicationService.listUsersWithRoles(criteria, page, size);
        long totalElements = userApplicationService.countUsers(criteria);

        PagedResponse<UserWithRolesDTO> response = new PagedResponse<>(users, page, size, totalElements);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countUsers(@ModelAttribute UserSearchCriteria criteria) {
        long count = userApplicationService.countUsers(criteria);
        return ResponseEntity.ok(count);
    }
}