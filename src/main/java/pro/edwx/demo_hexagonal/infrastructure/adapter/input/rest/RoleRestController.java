package pro.edwx.demo_hexagonal.infrastructure.adapter.input.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.edwx.demo_hexagonal.application.dto.role.CreateRoleRequestDTO;
import pro.edwx.demo_hexagonal.application.dto.role.RoleDTO;
import pro.edwx.demo_hexagonal.application.dto.role.UpdateRoleRequestDTO;
import pro.edwx.demo_hexagonal.application.service.RoleApplicationService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/roles")
public class RoleRestController {

    private final RoleApplicationService roleApplicationService;

    @PostMapping
    public ResponseEntity<RoleDTO> createRole(@Valid @RequestBody CreateRoleRequestDTO requestDTO) {
        RoleDTO createdRole = roleApplicationService.createRole(requestDTO);
        return new ResponseEntity<>(createdRole, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> getRole(@PathVariable Long id) {
        RoleDTO role = roleApplicationService.getRole(id);
        return ResponseEntity.ok(role);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleDTO> updateRole(@PathVariable Long id, @Valid @RequestBody UpdateRoleRequestDTO requestDTO) {
        RoleDTO updatedRole = roleApplicationService.updateRole(id, requestDTO);
        return ResponseEntity.ok(updatedRole);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        roleApplicationService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<RoleDTO>> listRoles() {
        List<RoleDTO> roles = roleApplicationService.listRoles();
        return ResponseEntity.ok(roles);
    }
}
