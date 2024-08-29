package pro.edwx.demo_hexagonal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pro.edwx.demo_hexagonal.domain.model.Role;
import pro.edwx.demo_hexagonal.domain.model.User;
import pro.edwx.demo_hexagonal.domain.port.output.PasswordEncoder;
import pro.edwx.demo_hexagonal.domain.port.output.RoleRepository;
import pro.edwx.demo_hexagonal.domain.port.output.UserRepository;

@SpringBootApplication
public class DemoHexagonalApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoHexagonalApplication.class, args);
	}

    @Bean
    public CommandLineRunner initData(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            Role adminRole = Role.builder()
                .name("ROLE_ADMIN")
                .description("Administrator role")
                .build();
            Role adminRoleCreated = roleRepository.save(adminRole);

            Role userRole = Role.builder()
                .name("ROLE_USER")
                .description("User role")
                .build();
            Role userRoleCreated = roleRepository.save(userRole);

            User userAdmin = User.builder()
                .username("admin")
                .email("admin@edwx.pro")
                .password(passwordEncoder.encode("admin"))
                .build();
            userAdmin.addRole(adminRoleCreated);
            userRepository.save(userAdmin);

            User userBasic = User.builder()
                .username("user")
                .email("user@edwx.pro")
                .password(passwordEncoder.encode("user"))
                .build();

            userBasic.addRole(userRoleCreated);
            userRepository.save(userBasic);
        };
    }
}
